package org.colin.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.colin.websocket.WebSocketServerEndpoint;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.io.IOException;
/**
 * @Description: 监听类
 * @ClassName: ConsumerConfig
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 10:47
 * @Version: 1.1.0
 */
@Configuration
@EnableRabbit
@Slf4j
public class ConsumerConfig implements RabbitListenerConfigurer {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private WebSocketServerEndpoint webSocketServerEndpoint;

    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    /**
     * 监听消息发送
     *
     * @param transaction
     * @param contract
     * @param qualification
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(@Qualifier("queueTransaction") Queue transaction, @Qualifier("queueContract") Queue contract, @Qualifier("queueQualification") Queue qualification) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(transaction, contract, qualification);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                // 获取消息体
                byte[] body = message.getBody();
                log.info("监听到消息: " + new String(body));
                try {
                    // 通过websocket向前端发送消息
                    webSocketServerEndpoint.sendMessageToAll(new String(body));
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认消息成功消费
                } catch (IOException e) {
                    log.error("消息推送前台出错：" + e.getMessage() + "/r/n重新发送");
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); //重新发送
                }
            }
        });
        return container;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }
}
