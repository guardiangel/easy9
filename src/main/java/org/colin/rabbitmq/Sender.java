package org.colin.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.colin.constants.RabbitConstant;
import org.colin.websocket.BroadcastMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
/**
 * @Description: Sender
 * @ClassName: Sender
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 10:52
 * @Version: 1.1.0
 */
@Component
@Slf4j
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //消息发送确认回调方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息发送成功:" + correlationData);
    }

    //消息发送失败回调方法
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息发送失败:" + new String(message.getBody()));
    }

    /**
     * 发送消息，不需要实现任何接口，供外部调用
     */
    public void send(BroadcastMessage messageVo) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // 将消息类转为json发送
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE, RabbitConstant.RK_CONTRACT, JSONObject.toJSON(messageVo).toString(), correlationId);
    }
}
