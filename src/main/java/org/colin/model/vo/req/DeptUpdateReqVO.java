package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * @Description: DeptUpdateReqVO
 * @ClassName: DeptUpdateReqVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/21 15:32
 * @Version: 1.1.0
 */
public class DeptUpdateReqVO {

    @ApiModelProperty(value = "部门ID")
    @NotBlank(message = "部门ID不能为空")
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private String pid;

    @ApiModelProperty(value = "部门状态(1:正常；0:弃用)")
    private Integer status;

    @ApiModelProperty(value = "部门经理ID")
    private String deptManagerId;

    @ApiModelProperty(value = "部门经理名称")
    private String managerName;

    @ApiModelProperty(value = "部门经理电话")
    private String phone;

    @Override
    public String toString() {
        return "DeptUpdateReqVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", status=" + status +
                ", deptManagerId='" + deptManagerId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeptManagerId() {
        return deptManagerId;
    }

    public void setDeptManagerId(String deptManagerId) {
        this.deptManagerId = deptManagerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
