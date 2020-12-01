package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @ClassName: SysSuggestionPageReqVO
 * @Author: wujiangbo
 * @Date: 2020/7/4 0004 15:53
 * @Version: 1.1.0
 */
public class SysSuggestionPageReqVO implements Serializable {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "意见反馈时间")
    private Date updateTime;

    @ApiModelProperty(value = "意见反馈人ID")
    private String updateUserId;

    @ApiModelProperty(value = "意见反馈提交人姓名")
    private String updateUserIdName;

    @ApiModelProperty(value = "建议/意见内容")
    private String content;

    @ApiModelProperty(value = "来源（1：平台；2：北方社区）")
    private String source;

    @ApiModelProperty(value = "类型（1：意见反馈；2：喜欢我们；3：商务合作；）")
    private String type;

    @Override
    public String toString() {
        return "SysSuggestionPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserIdName='" + updateUserIdName + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
