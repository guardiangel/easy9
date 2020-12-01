package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @Description:
 * @ClassName: EchartsRespVO
 * @Author: wujiangbo
 * @Date: 2020/7/20 18:29
 * @Version: 1.1.0
 */
public class EchartsRespVO {

    @ApiModelProperty(value = "名称")
    private List<String> name;

    @ApiModelProperty(value = "数量")
    private List<EchartsVO> count;

    @Override
    public String toString() {
        return "EchartsRespVO{" +
                "name=" + name +
                ", count=" + count +
                '}';
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<EchartsVO> getCount() {
        return count;
    }

    public void setCount(List<EchartsVO> count) {
        this.count = count;
    }
}
