package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Description: DeptPageReqVO
 * @ClassName: DeptPageReqVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/21 15:33
 * @Version: 1.1.0
 */
public class DeptPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @Override
    public String toString() {
        return "DeptPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
