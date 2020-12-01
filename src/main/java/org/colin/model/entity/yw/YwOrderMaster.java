package org.colin.model.entity.yw;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YwOrderMaster implements Serializable {

    @ApiModelProperty(value = "主键ID", example = "", hidden = false)
    private String id;

    @ApiModelProperty(value = "订单号", example = "", hidden = false)
    private String orderNumber;

    @ApiModelProperty(value = "买家电话", example = "", hidden = false)
    private String custPhone;

    @ApiModelProperty(value = "买家收件地址(楼栋号）", example = "", hidden = false)
    private String custBuilding;

    @ApiModelProperty(value = "订单总金额", example = "", hidden = false)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "创建时间", example = "", hidden = false)
    private Date createTime;

    @Override
    public String toString() {
        return "YwOrderMaster{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custBuilding='" + custBuilding + '\'' +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustBuilding() {
        return custBuilding;
    }

    public void setCustBuilding(String custBuilding) {
        this.custBuilding = custBuilding;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}