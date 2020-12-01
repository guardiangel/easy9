package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JxcOutStore implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间(出库时间)")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUser;

    @ApiModelProperty(value = "商品编号")
    private String goodsCode;

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

    @Override
    public String toString() {
        return "JxcOutStore{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", outCount=" + outCount +
                ", outPerson='" + outPerson + '\'' +
                ", outPersonPhone='" + outPersonPhone + '\'' +
                ", outDesc='" + outDesc + '\'' +
                ", whCode='" + whCode + '\'' +
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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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
}