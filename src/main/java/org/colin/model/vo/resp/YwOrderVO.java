package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @Description: YwOrderVO
 * @ClassName: YwOrderVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/8 15:25
 * @Version: 1.1.0
 */
public class YwOrderVO implements Serializable {

    @ApiModelProperty(value = "订单号")
    private String orderNum;

    @ApiModelProperty(value = "商家微信收款码二维码")
    private String weixinMoney;

    @ApiModelProperty(value = "商家联系人姓名", example = "", hidden = false)
    private String storeLinkUser;

    @ApiModelProperty(value = "商家联系方式", example = "", hidden = false)
    private String storeLinkPhone;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "YwOrderVO{" +
                "orderNum='" + orderNum + '\'' +
                ", weixinMoney='" + weixinMoney + '\'' +
                ", storeLinkUser='" + storeLinkUser + '\'' +
                ", storeLinkPhone='" + storeLinkPhone + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getWeixinMoney() {
        return weixinMoney;
    }

    public void setWeixinMoney(String weixinMoney) {
        this.weixinMoney = weixinMoney;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
