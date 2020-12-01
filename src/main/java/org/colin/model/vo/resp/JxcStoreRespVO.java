package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JxcStoreRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人ID")
    private String updateUser;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value = "商品型号")
    private String goodsModel;

    @ApiModelProperty(value = "商品类型(从数据字典获取)")
    private String goodsType;

    @ApiModelProperty(value = "商品类型描述")
    private String goodsTypeDesc;

    @ApiModelProperty(value = "库存总数量")
    private BigDecimal storeCount;

    @ApiModelProperty(value = "库存预警数量")
    private BigDecimal warnCount;

    @ApiModelProperty(value = "商品单位")
    private String goodsUnit;

    @ApiModelProperty(value = "仓库编号")
    private String whCode;

    @ApiModelProperty(value = "仓库名称")
    private String whCodeDesc;

    @Override
    public String toString() {
        return "JxcStoreRespVO{" +
                "id='" + id + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsModel='" + goodsModel + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsTypeDesc='" + goodsTypeDesc + '\'' +
                ", storeCount=" + storeCount +
                ", warnCount=" + warnCount +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", whCode='" + whCode + '\'' +
                ", whCodeDesc='" + whCodeDesc + '\'' +
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

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsTypeDesc() {
        return goodsTypeDesc;
    }

    public void setGoodsTypeDesc(String goodsTypeDesc) {
        this.goodsTypeDesc = goodsTypeDesc;
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

    public String getWhCodeDesc() {
        return whCodeDesc;
    }

    public void setWhCodeDesc(String whCodeDesc) {
        this.whCodeDesc = whCodeDesc;
    }
}
