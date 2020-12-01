package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysConfig implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "系统参数键")
    private String sysKey;

    @ApiModelProperty(value = "描述")
    private String sysDesc;

    @ApiModelProperty(value = "最后更新人")
    private String updateUserId;

    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "系统参数值")
    private String sysValue;

    @ApiModelProperty(value = "心灵鸡汤")
    private String xljt;

    @Override
    public String toString() {
        return "SysConfig{" +
                "id='" + id + '\'' +
                ", sysKey='" + sysKey + '\'' +
                ", sysDesc='" + sysDesc + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateTime=" + updateTime +
                ", sysValue='" + sysValue + '\'' +
                ", xljt='" + xljt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getXljt() {
        return xljt;
    }

    public void setXljt(String xljt) {
        this.xljt = xljt;
    }
}