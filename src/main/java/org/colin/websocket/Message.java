package org.colin.websocket;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: Message
 * @ClassName: Message
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 15:43
 * @Version: 1.1.0
 */
public class Message implements Serializable {

    private String content;

    private String userPhoto;

    private Map<String, List<String>> names;

    private Date date = new Date();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(String name, String msg) {
        this.content = name + " " + DateFormat.getDateTimeInstance().format(date) + "<br/> " + msg;
    }

    public Map<String, List<String>> getNames() {
        return names;
    }

    public void setNames(Map<String, List<String>> names) {
        this.names = names;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private static Gson gson = new Gson();

    public Message(String content, Map<String, List<String>> names, String userPhoto) {
        this.content = content;
        this.names = names;
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Message() {
    }
}
