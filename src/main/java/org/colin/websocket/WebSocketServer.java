package org.colin.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.colin.model.dto.ChatMessage;
import org.colin.model.entity.SysUser;
import org.colin.service.UserService;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : 欢乐聊天室
 * @date : 2020-09-17 09:04:07
 */
@Slf4j
@Component
@ServerEndpoint("/chatWebSocket/{userId}") //该注解表示该类被声明为一个webSocket终端
public class WebSocketServer {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<List<String>, Session> sessionPools = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Session> userSessionPools = new ConcurrentHashMap<>();

    public static UserService userService;

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId){
        //根据userId查询用户信息
        SysUser user = userService.detailInfo(userId);
        if(user != null){
            String userRealName = user.getRealName();//真实姓名
            List<String> userInfoList = new ArrayList<>();
            userInfoList.add(userRealName);
            userInfoList.add(user.getPhoto());
            sessionPools.put(userInfoList, session);
            userSessionPools.put(userRealName, session);
            addOnlineCount();
            log.info("欢乐聊天室-用户[{}]加入了WebSocket！当前人数={}", userRealName, getOnlineNumber());
            // 广播上线消息
            ChatMessage msg = new ChatMessage();
            msg.setDate(new Date());
            msg.setTo("0");
            msg.setText(userRealName);
            msg.setUserPhoto(user.getPhoto());
            broadcast(JSON.toJSONString(msg,true));
        }
    }

    //发送消息
    public void sendMessage(Session session, String message) {
        if(session != null){
            synchronized (session) {
                try {
                    ChatMessage msg = JSONObject.parseObject(message, ChatMessage.class);
                    log.info("欢乐聊天室-发送内容={}", msg.toString());
                    session.getBasicRemote().sendText(message);
                }catch (Exception e){
                    log.error("发送信息时发生异常:{}", e);
                }
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String userName, String message){
        Session session = userSessionPools.get(userName);
        sendMessage(session, message);
    }

    // 群发消息
    public void broadcast(String message){
        for (Session session: userSessionPools.values()) {
            sendMessage(session, message);
        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "userId") String userId){
        //根据userId查询用户信息
        SysUser user = userService.detailInfo(userId);
        if(user != null){
            String userName = user.getRealName();
            List<String> userInfoList = new ArrayList<>();
            userInfoList.add(userName);
            userInfoList.add(user.getPhoto());
            sessionPools.remove(userInfoList);
            userSessionPools.remove(userName);
            subOnlineCount();
            log.info("欢乐聊天室-用户[{}]断开了WebSocket连接！当前人数={}", userName, getOnlineNumber());
            // 广播下线消息
            ChatMessage msg = new ChatMessage();
            msg.setDate(new Date());
            msg.setTo("-2");
            msg.setText(userName);
            msg.setUserPhoto(user.getPhoto());
            broadcast(JSON.toJSONString(msg,true));
        }
    }

    //收到客户端信息后，根据接收人的username把消息推下去或者群发
    // to=-1群发消息
    @OnMessage
    public void onMessage(String message, @PathParam(value = "userId") String userId){
        log.info("欢乐聊天室-收到消息:{}", message);
        //根据userId查询用户信息
        SysUser user = userService.detailInfo(userId);
        if(user != null){
            ChatMessage msg = JSON.parseObject(message, ChatMessage.class);
            msg.setDate(new Date());
            msg.setUserPhoto(user.getPhoto());
            if (msg.getTo().equals("-1")) {
                broadcast(JSON.toJSONString(msg,true));
            } else {
                sendInfo(msg.getTo(), JSON.toJSONString(msg,true));
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        log.info("WebSocket发生错误:{}", throwable);
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    public static AtomicInteger getOnlineNumber() {
        return onlineNum;
    }

    public static ConcurrentHashMap<List<String>, Session> getSessionPools() {
        return sessionPools;
    }
}
