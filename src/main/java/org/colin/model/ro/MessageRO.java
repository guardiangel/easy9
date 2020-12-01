package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @Description: MessageRO
 * @ClassName: MessageRO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/4 15:45
 * @Version: 1.1.0
 */
public class MessageRO implements Serializable {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "消息标题")
    private String msgTitle;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "消息接收者JSON串")
    private String receiveUserJson;

    @ApiModelProperty(value = "消息目标（1：平台；2：北方社区）")
    private String target;

    @Override
    public String toString() {
        return "MessageRO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", msgContent='" + msgContent + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", receiveUserJson='" + receiveUserJson + '\'' +
                ", target='" + target + '\'' +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReceiveUserJson() {
        return receiveUserJson;
    }

    public void setReceiveUserJson(String receiveUserJson) {
        this.receiveUserJson = receiveUserJson;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
