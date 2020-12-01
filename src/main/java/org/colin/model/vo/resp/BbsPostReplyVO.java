package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @ClassName: BbsPostReplyVO
 * @Author: wujiangbo
 * @Date: 2020/6/29 0029 10:40
 * @Version: 1.1.0
 */
public class BbsPostReplyVO implements Serializable {

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

    @ApiModelProperty(value = "发表评论时间")
    private Date publishTime;

    @ApiModelProperty(value = "发表评论人ID")
    private String publishUserId;

    @ApiModelProperty(value = "发表评论人姓名")
    private String publishUserIdName;

    @ApiModelProperty(value = "发表评论人头像")
    private String publishUserPhoto;

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

    @ApiModelProperty(value = "发布人积分数量")
    private Integer point;

    @ApiModelProperty(value = "发布人性别")
    private Integer sex;

    @ApiModelProperty(value = "发布人等级")
    private String level;

    @ApiModelProperty(value = "发布人等级名称")
    private String levelName;

    @Override
    public String toString() {
        return "BbsPostReplyVO{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserIdName='" + updateUserIdName + '\'' +
                ", postId='" + postId + '\'' +
                ", publishTime=" + publishTime +
                ", publishUserId='" + publishUserId + '\'' +
                ", publishUserIdName='" + publishUserIdName + '\'' +
                ", publishUserPhoto='" + publishUserPhoto + '\'' +
                ", content='" + content + '\'' +
                ", pid='" + pid + '\'' +
                ", state='" + state + '\'' +
                ", replyType='" + replyType + '\'' +
                ", reason='" + reason + '\'' +
                ", point=" + point +
                ", sex=" + sex +
                ", level='" + level + '\'' +
                ", levelName='" + levelName + '\'' +
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

    public String getPublishUserPhoto() {
        return publishUserPhoto;
    }

    public void setPublishUserPhoto(String publishUserPhoto) {
        this.publishUserPhoto = publishUserPhoto;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
