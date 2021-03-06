package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
/**
 * @Description: UserRoleOperationReqVO
 * @ClassName: UserRoleOperationReqVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:49
 * @Version: 1.1.0
 */
public class UserRoleOperationReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<String> roleIds;

    @Override
    public String toString() {
        return "UserRoleOperationReqVO{" +
                "userId='" + userId + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
