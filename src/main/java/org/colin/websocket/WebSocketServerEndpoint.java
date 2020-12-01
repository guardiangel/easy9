package org.colin.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 系统广播
 * @Author: wujiangbo(QQ:1134135987)
 * @Date: 2020/5/18 10:43
 * @Version: 1.1.0
 */
//使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
//虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。

/**
 * ServerEndpoint注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端，注解的值将被用于监听用户连接的终端访问URL地址，客户端可以通过这个URL连接到websocket服务器端
 */
@ServerEndpoint("/noticeWebSocket/{userId}") //WebSocket客户端建立连接的地址
@Component
@Slf4j
public class WebSocketServerEndpoint {

    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 未发送出的消息
     */
    public static Map<String, Map<String, String>> unSensMessages = new ConcurrentHashMap<>();

    /**
     * 建立连接的回调方法
     * @param session 与客户端的WebSocket连接会话
     * @param userId  用户ID，WebSocket支持路径参数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        // 建立连接时 保存当前登陆人到已登录集合中
        livingSessions.put(userId, session);
        // 判断当前登陆人是否有未读的消息  有则发送
        Iterator<Map.Entry<String, Map<String, String>>> iterator = unSensMessages.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, String>> next = iterator.next();
            Map<String, String> nextValue = next.getValue();
            Iterator<Map.Entry<String, String>> iterator1 = nextValue.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<String, String> next1 = iterator1.next();
                if (next1.getKey().equals(userId)) {
                    sendMessage(session, next1.getValue());
                    //移除已发送的消息
                    iterator1.remove();
                }
            }
            if (next.getValue().size() == 0) {
                // 当前事件已全部发送 移除该事件
                iterator.remove();
            }
        }
        log.info("系统广播=连接总数:{}", livingSessions.size());
    }

    //接收到信息
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {
        log.info("系统广播=" + userId + " : " + message);
        sendMessageToAll(userId + " : " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{}", error);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        livingSessions.remove(userId);
        log.info(userId + "系统广播=关闭连接，剩余连接总数:{}", livingSessions.size());
    }

    /**
     * 单独发送消息
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message.replace('\"', '\''));
        } catch (IOException e) {
            log.error("单独发送消息失败:{}", e);
        }
    }

    /**
     * 群发消息
     * userIds 是需要接收消息的用户id集合 可单发，可群发
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        // 将json字符串转为message类
        BroadcastMessage messageVo = JSONObject.parseObject(message, BroadcastMessage.class);
        // 需要发送的人可能未上线 将未发送的消息保存到未发送集合中
        Map<String, String> unSendsUsers = new ConcurrentHashMap<>();
        // 遍历需要发送到的人
        for (String userId : messageVo.getUserIds()) {
            // 当前已登录的人
            if (livingSessions.get(String.valueOf(userId)) != null) {
                //当前user已登录，发送消息
                sendMessage(livingSessions.get(String.valueOf(userId)), message);
            } else {
                // 当前user未登录，保存到集合中
                unSendsUsers.put(String.valueOf(userId), message);
            }
        }
        // 这些消息属于同一个事件,放入集合中
        unSensMessages.put(messageVo.getBussinessKey(), unSendsUsers);
    }
}
