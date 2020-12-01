package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WareHouseRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUser;

    @ApiModelProperty(value = "创建人真实姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "跟更新人ID")
    private String updateUser;

    @ApiModelProperty(value = "跟更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "仓库编号")
    private String whCode;

    @ApiModelProperty(value = "仓库名称")
    private String whName;

    @ApiModelProperty(value = "仓库地址")
    private String whAddress;

    @ApiModelProperty(value = "仓库邮编")
    private String whPost;

    @ApiModelProperty(value = "仓库联系人")
    private String whLinkUser;

    @ApiModelProperty(value = "仓库联系电话")
    private String whLinkPhone;

    @ApiModelProperty(value = "仓库简介")
    private String whBrief;

    @ApiModelProperty(value = "仓库建成时间")
    private Date whCreateTime;

    @ApiModelProperty(value = "仓库投入使用时间")
    private Date whUseTime;

    @Override
    public String toString() {
        return "WareHouseRespVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", whCode='" + whCode + '\'' +
                ", whName='" + whName + '\'' +
                ", whAddress='" + whAddress + '\'' +
                ", whPost='" + whPost + '\'' +
                ", whLinkUser='" + whLinkUser + '\'' +
                ", whLinkPhone='" + whLinkPhone + '\'' +
                ", whBrief='" + whBrief + '\'' +
                ", whCreateTime=" + whCreateTime +
                ", whUseTime=" + whUseTime +
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

    public String getWhPost() {
        return whPost;
    }

    public void setWhPost(String whPost) {
        this.whPost = whPost;
    }

    public String getWhLinkUser() {
        return whLinkUser;
    }

    public void setWhLinkUser(String whLinkUser) {
        this.whLinkUser = whLinkUser;
    }

    public String getWhLinkPhone() {
        return whLinkPhone;
    }

    public void setWhLinkPhone(String whLinkPhone) {
        this.whLinkPhone = whLinkPhone;
    }

    public String getWhBrief() {
        return whBrief;
    }

    public void setWhBrief(String whBrief) {
        this.whBrief = whBrief;
    }

    public Date getWhCreateTime() {
        return whCreateTime;
    }

    public void setWhCreateTime(Date whCreateTime) {
        this.whCreateTime = whCreateTime;
    }

    public Date getWhUseTime() {
        return whUseTime;
    }

    public void setWhUseTime(Date whUseTime) {
        this.whUseTime = whUseTime;
    }
}
