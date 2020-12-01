package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * @Description: UserPageUserByDeptReqVO
 * @ClassName: UserPageUserByDeptReqVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/21 15:28
 * @Version: 1.1.0
 */
public class UserPageUserByDeptReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "组织ID")
    @NotBlank(message = "组织ID不能为空")
    private String deptId;

    @Override
    public String toString() {
        return "UserPageUserByDeptReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", deptId='" + deptId + '\'' +
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
