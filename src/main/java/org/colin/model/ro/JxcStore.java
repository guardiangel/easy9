package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JxcStore implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人ID")
    private String updateUser;

    @ApiModelProperty(value = "商品编号")
    private String goodsCode;

    @ApiModelProperty(value = "库存总数量")
    private BigDecimal storeCount;

    @ApiModelProperty(value = "库存预警数量")
    private BigDecimal warnCount;

    @ApiModelProperty(value = "商品单位")
    private String goodsUnit;

    @ApiModelProperty(value = "仓库编号")
    private String whCode;

    @Override
    public String toString() {
        return "JxcStore{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", storeCount=" + storeCount +
                ", warnCount=" + warnCount +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", whCode='" + whCode + '\'' +
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public BigDecimal getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(BigDecimal storeCount) {
        this.storeCount = storeCount;
    }

    public BigDecimal getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(BigDecimal warnCount) {
        this.warnCount = warnCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }
}