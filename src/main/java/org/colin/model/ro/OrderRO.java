package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @Description: OrderRO
 * @ClassName: OrderRO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/5 16:15
 * @Version: 1.1.0
 */
public class OrderRO implements Serializable {

    @ApiModelProperty(value = "小区")
    private String communityId;

    @ApiModelProperty(value = "商家")
    private String storeId;

    @ApiModelProperty(value = "楼栋")
    private String buildingNumber;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 格式：商品名称:单价:数量#商品名称:单价:数量#商品名称:单价:数量
     */
    @ApiModelProperty(value = "商品信息")
    @NotBlank(message = "购买商品信息不能为空")
    private String goods;

    @Override
    public String toString() {
        return "OrderRO{" +
                "communityId='" + communityId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", goods='" + goods + '\'' +
                '}';
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
