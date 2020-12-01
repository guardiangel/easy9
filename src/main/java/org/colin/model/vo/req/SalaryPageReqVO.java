package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @ClassName: SalaryPageReqVO
 * @Author: wujiangbo
 * @Date: 2020/7/13 20:21
 * @Version: 1.1.0
 */
public class SalaryPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "工资发放年份")
    private String salaryYear;

    @ApiModelProperty(value = "工资发放月份")
    private String salaryMonth;

    @ApiModelProperty(value = "年份-月份")
    private String yearMonth;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    @Override
    public String toString() {
        return "SalaryPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", salaryYear='" + salaryYear + '\'' +
                ", salaryMonth='" + salaryMonth + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", deptName='" + deptName + '\'' +
                ", realName='" + realName + '\'' +
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

    public String getSalaryYear() {
        return salaryYear;
    }

    public void setSalaryYear(String salaryYear) {
        this.salaryYear = salaryYear;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
