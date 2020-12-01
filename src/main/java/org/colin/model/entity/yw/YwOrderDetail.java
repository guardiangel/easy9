package org.colin.model.entity.yw;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

public class YwOrderDetail implements Serializable {

    @ApiModelProperty(value = "主键ID", example = "", hidden = false)
    private String id;

    @ApiModelProperty(value = "订单号", example = "", hidden = false)
    private String orderNumber;

    @ApiModelProperty(value = "商品名称", example = "", hidden = false)
    private String goodsName;

    @ApiModelProperty(value = "商品单价", example = "", hidden = false)
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "购买数量", example = "", hidden = false)
    private Integer buyCount;

    @Override
    public String toString() {
        return "YwOrderDetail{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", buyCount=" + buyCount +
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
}