package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:
 * @ClassName: BbsPostVO
 * @Author: wujiangbo
 * @Date: 2020/6/23 0023 9:14
 * @Version: 1.1.0
 */
public class BbsPostVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "最后更新人姓名")
    private String updateUserIdName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "状态（0：正常；1：置顶；）")
    private String bbsType;

    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

    @ApiModelProperty(value = "点赞量")
    private Integer goodCount;

    @ApiModelProperty(value = "评论量")
    private Integer replyCount;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "发布人ID")
    private String publishUserId;

    @ApiModelProperty(value = "发布人姓名")
    private String publishUserName;

    @ApiModelProperty(value = "发布人头像")
    private String publishUserImg;

    @ApiModelProperty(value = "帖子状态（0：审核未通过；1：审核中；2:审核通过）")
    private String state;

    @ApiModelProperty(value = "帖子分类（1：社区广场；2：情感天空；3：顺风拼车；4：二手买卖；5：人生难题；6：技术交流；7：租房住房；）")
    private String postType;

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
        return "BbsPostVO{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserIdName='" + updateUserIdName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", bbsType='" + bbsType + '\'' +
                ", readCount=" + readCount +
                ", goodCount=" + goodCount +
                ", replyCount=" + replyCount +
                ", publishTime=" + publishTime +
                ", publishUserId='" + publishUserId + '\'' +
                ", publishUserName='" + publishUserName + '\'' +
                ", publishUserImg='" + publishUserImg + '\'' +
                ", state='" + state + '\'' +
                ", postType='" + postType + '\'' +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBbsType() {
        return bbsType;
    }

    public void setBbsType(String bbsType) {
        this.bbsType = bbsType;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
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

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getPublishUserImg() {
        return publishUserImg;
    }

    public void setPublishUserImg(String publishUserImg) {
        this.publishUserImg = publishUserImg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
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
