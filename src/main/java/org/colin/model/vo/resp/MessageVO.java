package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: MessageVO
 * @ClassName: MessageVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/4 15:42
 * @Version: 1.1.0
 */
public class MessageVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "发送消息时间")
    private Date createTime;

    @ApiModelProperty(value = "消息发送者ID")
    private String createUserId;

    @ApiModelProperty(value = "消息发送者")
    private String createUserName;

    @ApiModelProperty(value = "消息接受者ID")
    private String receiveUserId;

    @ApiModelProperty(value = "消息接受者")
    private String receiveUserName;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "消息标题")
    private String msgTitle;

    @ApiModelProperty(value = "消息状态（0：未读；1：已读；）")
    private Integer readStatus;

    @ApiModelProperty(value = "消息目标（1：平台；2：北方社区）")
    private String target;

    @Override
    public String toString() {
        return "MessageVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", receiveUserId='" + receiveUserId + '\'' +
                ", receiveUserName='" + receiveUserName + '\'' +
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
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
