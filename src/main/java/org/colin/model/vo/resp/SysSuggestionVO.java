package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description:
 * @ClassName: SysSuggestionVO
 * @Author: wujiangbo
 * @Date: 2020/7/4 0004 15:58
 * @Version: 1.1.0
 */
public class SysSuggestionVO {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "最后更新人姓名")
    private String updateUserIdName;

    @ApiModelProperty(value = "建议/意见内容")
    private String content;

    @ApiModelProperty(value = "来源（1：平台；2：北方社区）")
    private String source;

    @ApiModelProperty(value = "类型（1：意见反馈；2：喜欢我们；3：商务合作；）")
    private String type;

    @Override
    public String toString() {
        return "SysSuggestionVO{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserIdName='" + updateUserIdName + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
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
