package org.colin.model.entity.yw;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class YwStore implements Serializable {

    @ApiModelProperty(value = "主键ID", example = "", hidden = false)
    private String id;

    @NotBlank(message = "商家名称不能为空")
    @ApiModelProperty(value = "商家名称", example = "", hidden = false)
    private String storeName;

    @ApiModelProperty(value = "商家联系人姓名", example = "", hidden = false)
    private String storeLinkUser;

    @ApiModelProperty(value = "商家联系方式", example = "", hidden = false)
    private String storeLinkPhone;

    @ApiModelProperty(value = "商家声明", example = "", hidden = false)
    private String storeStatement;

    @ApiModelProperty(value = "小区ID", example = "", hidden = false)
    private String communityId;

    @ApiModelProperty(value = "创建时间", example = "", hidden = false)
    private Date createTime;

    @ApiModelProperty(value = "序号", example = "", hidden = false)
    private Integer orderNum;

    @ApiModelProperty(value = "微信收款二维码", example = "", hidden = false)
    private String receiveMoney;

    @ApiModelProperty(value = "状态（0：禁用；1：正常；）", example = "", hidden = false)
    private Integer storeState;

    @Override
    public String toString() {
        return "YwStore{" +
                "id='" + id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeLinkUser='" + storeLinkUser + '\'' +
                ", storeLinkPhone='" + storeLinkPhone + '\'' +
                ", storeStatement='" + storeStatement + '\'' +
                ", communityId='" + communityId + '\'' +
                ", createTime=" + createTime +
                ", orderNum=" + orderNum +
                ", receiveMoney='" + receiveMoney + '\'' +
                ", storeState=" + storeState +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLinkUser() {
        return storeLinkUser;
    }

    public void setStoreLinkUser(String storeLinkUser) {
        this.storeLinkUser = storeLinkUser;
    }

    public String getStoreLinkPhone() {
        return storeLinkPhone;
    }

    public void setStoreLinkPhone(String storeLinkPhone) {
        this.storeLinkPhone = storeLinkPhone;
    }

    public String getStoreStatement() {
        return storeStatement;
    }

    public void setStoreStatement(String storeStatement) {
        this.storeStatement = storeStatement;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(String receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }
}