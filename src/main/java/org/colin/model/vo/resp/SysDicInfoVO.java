package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: SysDicInfoVO
 * @ClassName: SysDicInfoVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/21 17:34
 * @Version: 1.1.0
 */
@ApiModel(description = "数据字典类型返回数据模型")
public class SysDicInfoVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "字典类型编码")
    private String typeCode;

    @ApiModelProperty(value = "字典编码")
    private String dicCode;

    @ApiModelProperty(value = "数据字典名称")
    private String dicValue;

    @ApiModelProperty(value = "备用字段")
    private String bak;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后更新人ID")
    private String updateUserId;

    @ApiModelProperty(value = "最后更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "状态(0：不可用；1：可用)")
    private Integer status;

    @Override
    public String toString() {
        return "SysDicInfoVO{" +
                "id='" + id + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", dicValue='" + dicValue + '\'' +
                ", bak='" + bak + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
