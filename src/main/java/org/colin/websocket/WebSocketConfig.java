package org.colin.websocket;

import org.colin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description: 注入ServerEndpointExporter，这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint。 要注意，如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不要注入ServerEndpointExporter， 因为它将由容器自己提供和管理
 * @ClassName: WebSocketConfig
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 10:42
 * @Version: 1.1.0
 */
@Configuration
@ComponentScan("org.colin.websocket")
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setUserService(UserService userService) {
        OnlineWebSocket.userService = userService;
        WebSocketServer.userService = userService;
    }
}
