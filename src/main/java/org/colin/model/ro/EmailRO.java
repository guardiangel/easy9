package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.colin.model.dto.EmailNoticeDto;

import java.io.Serializable;
/**
 * @Description: 发送邮件的JavaBean实体类
 * @ClassName: EmailRO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/29 17:24
 * @Version: 1.1.0
 */
public class EmailRO implements Serializable {

    @ApiModelProperty(value = "接收者邮箱", example = "", hidden = false)
    private String to;

    @ApiModelProperty(value = "主题", example = "", hidden = false)
    private String object;

    @ApiModelProperty(value = "邮件内容", example = "", hidden = false)
    private String content;

    @ApiModelProperty(value = "邮件对象", example = "", hidden = false)
    private EmailNoticeDto dto;

    @Override
    public String toString() {
        return "EmailRO{" +
                "to='" + to + '\'' +
                ", object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", dto=" + dto +
                '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailNoticeDto getDto() {
        return dto;
    }

    public void setDto(EmailNoticeDto dto) {
        this.dto = dto;
    }
}
