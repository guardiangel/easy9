package org.colin.websocket;

import lombok.extern.slf4j.Slf4j;
import org.colin.model.entity.SysUser;
import org.colin.service.UserService;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description: 统计在线人数
 * @author: wujiangbo(QQ:1134135987)
 * @date: 2020-09-17 16:37:24
 */
@Slf4j
@Component
@ServerEndpoint("/onLineWebsocket/{userId}")  //该注解表示该类被声明为一个webSocket终端
public class OnlineWebSocket {

    /**
     * 初始在线人数
     */
    private static int online_num = 0;

    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<OnlineWebSocket> webSocketSet = new CopyOnWriteArraySet<OnlineWebSocket>();

    /**
     * 会话
     */
    private Session session;

    public static UserService userService;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        //根据userId查询用户信息
        SysUser user = userService.detailInfo(userId);
        if (null != user) {
            String username = user.getRealName();
            log.info("统计在线人数-用户[{}]登录系统，当前在线人数={}", username, getOnline_num());
            send(getOnline_num() + "###" + username + "###up");
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        webSocketSet.remove(this);
        subOnlineCount();
        //根据userId查询用户信息
        SysUser user = userService.detailInfo(userId);
        if (null != user) {
            String username = user.getRealName();
            log.info("统计在线人数-用户[{}]退出系统，当前在线人数={}", username, getOnline_num());
            send(getOnline_num() + "###" + username + "###down");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自客户端的消息:" + message);
        send(message);
    }

    //发送消息
    public void send(String message) {
        for (OnlineWebSocket webSocket : webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error("WebSocket发送消息异常:{}", e);
            }
        }
    }

    public synchronized int getOnline_num() {
        return OnlineWebSocket.online_num;
    }

    public synchronized int subOnlineCount() {
        return OnlineWebSocket.online_num--;
    }

    public synchronized int addOnlineCount() {
        return OnlineWebSocket.online_num++;
    }
}
