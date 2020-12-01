package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JxcOutStoreRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间(出库时间)")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUser;

    @ApiModelProperty(value = "创建人真实姓名")
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

    @ApiModelProperty(value = "出库数量")
    private BigDecimal outCount;

    @ApiModelProperty(value = "商品领用人")
    private String outPerson;

    @ApiModelProperty(value = "商品领用人联系方式")
    private String outPersonPhone;

    @ApiModelProperty(value = "出库描述")
    private String outDesc;

    @ApiModelProperty(value = "仓库编号")
    private String whCode;

    @ApiModelProperty(value = "仓库名称")
    private String whCodeDesc;

    @Override
    public String toString() {
        return "JxcOutStoreRespVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsModel='" + goodsModel + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsTypeDesc='" + goodsTypeDesc + '\'' +
                ", outCount=" + outCount +
                ", outPerson='" + outPerson + '\'' +
                ", outPersonPhone='" + outPersonPhone + '\'' +
                ", outDesc='" + outDesc + '\'' +
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

    public BigDecimal getOutCount() {
        return outCount;
    }

    public void setOutCount(BigDecimal outCount) {
        this.outCount = outCount;
    }

    public String getOutPerson() {
        return outPerson;
    }

    public void setOutPerson(String outPerson) {
        this.outPerson = outPerson;
    }

    public String getOutPersonPhone() {
        return outPersonPhone;
    }

    public void setOutPersonPhone(String outPersonPhone) {
        this.outPersonPhone = outPersonPhone;
    }

    public String getOutDesc() {
        return outDesc;
    }

    public void setOutDesc(String outDesc) {
        this.outDesc = outDesc;
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
