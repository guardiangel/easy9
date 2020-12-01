package org.colin.model.dto;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : 群聊消息对象
 * @date : 2020-09-17 09:05:15
 */
public class ChatMessage {

    //发送者name
    public String from;
    //接收者name
    public String to;
    //发送的文本
    public String text;
    //用户头像
    public String userPhoto;
    //发送时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date date;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                ", date=" + DateUtil.format(date, "yyyy-MM-dd HH:mm:ss") +
                '}';
    }
}
