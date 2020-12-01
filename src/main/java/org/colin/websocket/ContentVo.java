package org.colin.websocket;

import java.io.Serializable;
/**
 * @Description: ContentVo
 * @ClassName: ContentVo
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 15:45
 * @Version: 1.1.0
 */
public class ContentVo implements Serializable {

    private String to;

    private String msg;

    private Integer type;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
