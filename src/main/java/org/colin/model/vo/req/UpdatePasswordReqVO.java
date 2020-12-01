package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @ClassName: UpdatePasswordReqVO
 * @Author: wujiangbo
 * @Date: 2020/7/4 0004 10:52
 * @Version: 1.1.0
 */
public class UpdatePasswordReqVO {

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String emailCode;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String password;

    @Override
    public String toString() {
        return "UpdatePasswordReqVO{" +
                "emailCode='" + emailCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
