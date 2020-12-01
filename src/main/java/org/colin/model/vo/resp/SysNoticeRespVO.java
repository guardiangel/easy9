package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysNoticeRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUser;

    @ApiModelProperty(value = "最后更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "发布内容")
    private String noticeContent;

    @ApiModelProperty(value = "发布类型")
    private String type;

    @ApiModelProperty(value = "发布类型名称")
    private String typeName;

    @ApiModelProperty(value = "备用字段1")
    private String bak1;

    @ApiModelProperty(value = "备用字段2")
    private String bak2;

    @Override
    public String toString() {
        return "SysNoticeRespVO{" +
                "id='" + id + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", updateTime=" + updateTime +
                ", noticeContent='" + noticeContent + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", bak1='" + bak1 + '\'' +
                ", bak2='" + bak2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1;
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2;
    }
}
