package org.colin.model.entity.yw;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

public class BbsPostReply implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateUserId;

    @ApiModelProperty(value = "帖子ID")
    private String postId;

    @ApiModelProperty(value = "发表评论时间")
    private Date publishTime;

    @ApiModelProperty(value = "发表评论人ID")
    private String publishUserId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "父评论ID")
    private String pid;

    @ApiModelProperty(value = "评论状态（0：审核未通过；1：审核中；2:审核通过）")
    private String state;

    @ApiModelProperty(value = "评论状态（0：正常；1：置顶；）")
    private String replyType;

    @ApiModelProperty(value = "审核不通过原因")
    private String reason;

    @Override
    public String toString() {
        return "BbsPostReply{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", postId='" + postId + '\'' +
                ", publishTime=" + publishTime +
                ", publishUserId='" + publishUserId + '\'' +
                ", content='" + content + '\'' +
                ", pid='" + pid + '\'' +
                ", state='" + state + '\'' +
                ", replyType='" + replyType + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}