package org.colin.model.dto;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : 北方社区首页返回信息
 * @date : 2020-09-18 11:31:29
 */
public class BbsInitVO {

    //服务器当前时间
    private String currentTime;

    //心灵鸡汤文本
    private String xljt;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getXljt() {
        return xljt;
    }

    public void setXljt(String xljt) {
        this.xljt = xljt;
    }
}
