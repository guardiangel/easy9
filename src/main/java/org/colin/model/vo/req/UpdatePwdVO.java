package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @ClassName: UpdatePwdVO
 * @Author: wujiangbo
 * @Date: 2020/7/4 0004 10:59
 * @Version: 1.1.0
 */
public class UpdatePwdVO {

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "时间校验串")
    @NotBlank(message = "时间校验串不能为空")
    private String time;

    @Override
    public String toString() {
        return "UpdatePwdVO{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
