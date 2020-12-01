package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.colin.model.entity.SysRole;

import java.util.List;
/**
 * @Description: UserOwnRoleRespVO
 * @ClassName: UserOwnRoleRespVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/20 19:44
 * @Version: 1.1.0
 */
public class UserOwnRoleRespVO {

    @ApiModelProperty("所有角色集合")
    private List<SysRole> allRole;

    @ApiModelProperty(value = "用户所拥有角色集合")
    private List<String> ownRoles;

    @Override
    public String toString() {
        return "UserOwnRoleRespVO{" +
                "allRole=" + allRole +
                ", ownRoles=" + ownRoles +
                '}';
    }

    public List<SysRole> getAllRole() {
        return allRole;
    }

    public void setAllRole(List<SysRole> allRole) {
        this.allRole = allRole;
    }

    public List<String> getOwnRoles() {
        return ownRoles;
    }

    public void setOwnRoles(List<String> ownRoles) {
        this.ownRoles = ownRoles;
    }
}
