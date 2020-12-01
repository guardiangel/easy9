package org.colin.websocket;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * @Description: 广播消息的实体类
 * @ClassName: BroadcastMessage
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/20 15:09
 * @Version: 1.1.0
 */
@Data
public class BroadcastMessage implements Serializable {

    //消息的唯一标识
    private String bussinessKey;

    //消息内容
    private String message;

    //发送广播消息的用户名
    private String username;

    //可以收到的用户id
    private List<String> userIds;

    // 时间
    private String date;
}
