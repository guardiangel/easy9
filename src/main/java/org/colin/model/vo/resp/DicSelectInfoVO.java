package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @ClassName: DicSelectInfoVO
 * @Author: wujiangbo
 * @Date: 2020/7/21 0021 18:24
 * @Version: 1.1.0
 */
public class DicSelectInfoVO {

    @ApiModelProperty(value = "字典类型编码")
    private String typeCode;

    @ApiModelProperty(value = "数据字典编码和名称集合")
    private List<DicInfo> dicInfo;

    @Override
    public String toString() {
        return "DicSelectInfoVO{" +
                "typeCode='" + typeCode + '\'' +
                ", dicInfo=" + dicInfo +
                '}';
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public List<DicInfo> getDicInfo() {
        return dicInfo;
    }

    public void setDicInfo(List<DicInfo> dicInfo) {
        this.dicInfo = dicInfo;
    }
}
