package org.colin.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: SysRolePermission
 * @ClassName: SysRolePermission
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:37
 * @Version: 1.1.0
 */
public class SysRolePermission implements Serializable {

    private String id;

    private String roleId;

    private String permissionId;

    private Date createTime;

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}