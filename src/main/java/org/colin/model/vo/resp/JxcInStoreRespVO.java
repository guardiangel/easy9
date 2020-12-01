package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JxcInStoreRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUser;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

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

    @ApiModelProperty(value = "入库数量")
    private BigDecimal inCount;

    @ApiModelProperty(value = "入库单位")
    private String goodsUnit;

    @ApiModelProperty(value = "入库单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "入库总价")
    private BigDecimal goodsTotalPrice;

    @ApiModelProperty(value = "商品入库人姓名")
    private String inPerson;

    @ApiModelProperty(value = "商品入库人联系方式")
    private String inPersonPhone;

    @ApiModelProperty(value = "商品规格")
    private String goodsSpec;

    @ApiModelProperty(value = "入库描述")
    private String inDesc;

    @ApiModelProperty(value = "仓库编号")
    private String whCode;

    @ApiModelProperty(value = "仓库名称")
    private String whCodeDesc;

    @Override
    public String toString() {
        return "JxcInStoreRespVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsModel='" + goodsModel + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsTypeDesc='" + goodsTypeDesc + '\'' +
                ", inCount=" + inCount +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsTotalPrice=" + goodsTotalPrice +
                ", inPerson='" + inPerson + '\'' +
                ", inPersonPhone='" + inPersonPhone + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", inDesc='" + inDesc + '\'' +
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

    public BigDecimal getInCount() {
        return inCount;
    }

    public void setInCount(BigDecimal inCount) {
        this.inCount = inCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public String getInPerson() {
        return inPerson;
    }

    public void setInPerson(String inPerson) {
        this.inPerson = inPerson;
    }

    public String getInPersonPhone() {
        return inPersonPhone;
    }

    public void setInPersonPhone(String inPersonPhone) {
        this.inPersonPhone = inPersonPhone;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getInDesc() {
        return inDesc;
    }

    public void setInDesc(String inDesc) {
        this.inDesc = inDesc;
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
