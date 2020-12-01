package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @Description:
 * @ClassName: SendEmailReqVO
 * @Author: wujiangbo
 * @Date: 2020/6/30 0030 17:20
 * @Version: 1.1.0
 */
public class SendEmailReqVO implements Serializable {

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "图片验证码")
    @NotBlank(message = "图片验证码不能为空")
    private String code;

    @ApiModelProperty(value = "获取图片验证码时携带的时间戳")
    @NotBlank(message = "时间戳不能为空")
    private String time;

    @Override
    public String toString() {
        return "SendEmailReqVO{" +
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
