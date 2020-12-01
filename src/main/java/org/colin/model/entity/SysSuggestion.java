package org.colin.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description:
 * @ClassName: SysSuggestion
 * @Author: wujiangbo
 * @Date: 2020/6/28 0028 16:07
 * @Version: 1.1.0
 */
public class SysSuggestion implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "建议/意见内容")
    private String content;

    @ApiModelProperty(value = "来源（1：平台；2：北方社区）")
    private String source;

    @ApiModelProperty(value = "类型（1：意见反馈；2：喜欢我们；3：商务合作；）")
    private String type;

    @ApiModelProperty(value = "商务合作联系方式")
    private String linkStyle;

    @ApiModelProperty(value = "商务合作需求描述")
    private String cooperationDesc;

    @Override
    public String toString() {
        return "SysSuggestion{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", linkStyle='" + linkStyle + '\'' +
                ", cooperationDesc='" + cooperationDesc + '\'' +
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

    public String getLinkStyle() {
        return linkStyle;
    }

    public void setLinkStyle(String linkStyle) {
        this.linkStyle = linkStyle;
    }

    public String getCooperationDesc() {
        return cooperationDesc;
    }

    public void setCooperationDesc(String cooperationDesc) {
        this.cooperationDesc = cooperationDesc;
    }
}
