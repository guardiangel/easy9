package org.colin.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class SysMessage implements Serializable {

    private String id;

    private Date createTime;

    private String createUserId;

    private String receiveUserId;

    private String msgContent;

    private String msgTitle;

    private Integer readStatus;

    @ApiModelProperty(value = "消息目标（1：平台；2：北方社区）")
    private String target;

    @Override
    public String toString() {
        return "SysMessage{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", receiveUserId='" + receiveUserId + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", readStatus=" + readStatus +
                ", target='" + target + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
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

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}