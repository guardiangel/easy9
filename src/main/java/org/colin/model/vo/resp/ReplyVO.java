package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description:
 * @ClassName: ReplyVO
 * @Author: wujiangbo
 * @Date: 2020/6/29 0029 17:04
 * @Version: 1.1.0
 */
public class ReplyVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "最后更新人姓名")
    private String updateUserIdName;

    @ApiModelProperty(value = "帖子ID")
    private String postId;

    @ApiModelProperty(value = "帖子分类（1：社区广场；2：情感天空；3：顺风拼车；4：二手买卖；5：人生难题；6：技术交流；7：租房住房；）")
    private String postType;

    @ApiModelProperty(value = "帖子标题")
    private String postTitle;

    @ApiModelProperty(value = "帖子内容")
    private String postContent;

    @ApiModelProperty(value = "发表评论时间")
    private Date publishTime;

    @ApiModelProperty(value = "发表评论人ID")
    private String publishUserId;

    @ApiModelProperty(value = "发表评论人姓名")
    private String publishUserIdName;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "父评论ID")
    private String pid;

    @ApiModelProperty(value = "帖子状态（0：审核未通过；1：审核中；2:审核通过）")
    private String state;

    @ApiModelProperty(value = "评论状态（0：正常；1：置顶；）")
    private String replyType;

    @ApiModelProperty(value = "审核不通过原因")
    private String reason;

    @ApiModelProperty(value = "评论人图像")
    private String publishUserImg;

    @Override
    public String toString() {
        return "ReplyVO{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserIdName='" + updateUserIdName + '\'' +
                ", postId='" + postId + '\'' +
                ", postType='" + postType + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", publishTime=" + publishTime +
                ", publishUserId='" + publishUserId + '\'' +
                ", publishUserIdName='" + publishUserIdName + '\'' +
                ", content='" + content + '\'' +
                ", pid='" + pid + '\'' +
                ", state='" + state + '\'' +
                ", replyType='" + replyType + '\'' +
                ", reason='" + reason + '\'' +
                ", publishUserImg='" + publishUserImg + '\'' +
                '}';
    }

    public String getPublishUserImg() {
        return publishUserImg;
    }

    public void setPublishUserImg(String publishUserImg) {
        this.publishUserImg = publishUserImg;
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

    public String getUpdateUserIdName() {
        return updateUserIdName;
    }

    public void setUpdateUserIdName(String updateUserIdName) {
        this.updateUserIdName = updateUserIdName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
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

    public String getPublishUserIdName() {
        return publishUserIdName;
    }

    public void setPublishUserIdName(String publishUserIdName) {
        this.publishUserIdName = publishUserIdName;
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

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
