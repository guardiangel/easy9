package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: LoginReqVO
 * @ClassName: LoginReqVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/16 21:16
 * @Version: 1.1.0
 */
public class LoginReqVO {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "登录账号不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;

    @ApiModelProperty(value = "登录类型(1:pc;2:App)")
    @NotBlank(message = "登录类型不能为空")
    private String type;

    @ApiModelProperty(value = "图片验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "获取图片验证码时携带的时间戳")
    @NotBlank(message = "时间戳不能为空")
    private String time;

    @Override
    public String toString() {
        return "LoginReqVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
