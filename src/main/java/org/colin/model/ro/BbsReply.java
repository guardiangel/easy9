package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:
 * @ClassName: BbsReply
 * @Author: wujiangbo
 * @Date: 2020/6/29 0029 19:40
 * @Version: 1.1.0
 */
public class BbsReply implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "帖子ID")
    private String postId;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "发布人ID")
    private String publishUserId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论上级ID")
    private String pid;

    @ApiModelProperty(value = "状态（0：审核未通过；1：审核中；2:审核通过）")
    private String state;

    @ApiModelProperty(value = "状态（0：正常；1：置顶；）")
    private String replyType;

    @ApiModelProperty(value = "审核不通过原因")
    private String reason;

    @ApiModelProperty(value = "审核通过时，奖励的积分数")
    private Integer point;

    @Override
    public String toString() {
        return "BbsReply{" +
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
                ", point=" + point +
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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
