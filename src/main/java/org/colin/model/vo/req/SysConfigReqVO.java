package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysConfigReqVO implements Serializable {

    @ApiModelProperty(value = "系统参数键")
    private String sysKey;

    @ApiModelProperty(value = "描述")
    private String sysDesc;

    @Override
    public String toString() {
        return "SysConfigReqVO{" +
                "sysKey='" + sysKey + '\'' +
                ", sysDesc='" + sysDesc + '\'' +
                '}';
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
}
