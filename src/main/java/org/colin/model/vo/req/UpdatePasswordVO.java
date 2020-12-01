package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @ClassName: UpdatePasswordVO
 * @Author: wujiangbo
 * @Date: 2020/7/15 16:28
 * @Version: 1.1.0
 */
public class UpdatePasswordVO {

    @ApiModelProperty(value = "原密码")
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "重新输入密码")
    @NotBlank(message = "重新输入密码不能为空")
    private String newPassword2;

    @Override
    public String toString() {
        return "UpdatePasswordVO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newPassword2='" + newPassword2 + '\'' +
                '}';
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
