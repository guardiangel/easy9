package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Description:
 * @ClassName: DicInfo
 * @Author: wujiangbo
 * @Date: 2020/7/21 0021 18:52
 * @Version: 1.1.0
 */
public class DicInfo {

    @ApiModelProperty(value = "字典编码")
    private String dicCode;

    @ApiModelProperty(value = "数据字典名称")
    private String dicValue;

    @Override
    public String toString() {
        return "DicInfo{" +
                "dicCode='" + dicCode + '\'' +
                ", dicValue='" + dicValue + '\'' +
                '}';
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
}
