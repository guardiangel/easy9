package org.colin.model.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: SysUserRole
 * @ClassName: SysUserRole
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:40
 * @Version: 1.1.0
 */
public class SysUserRole implements Serializable {

    private String id;

    private String userId;

    private String roleId;

    private Date createTime;

    @Override
    public String toString() {
        return "SysUserRole{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
