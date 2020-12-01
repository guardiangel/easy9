package org.colin.model.entity.yw;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 * @Description: YwCommunity
 * @ClassName: YwCommunity
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/6 11:16
 * @Version: 1.1.0
 */
public class YwCommunity {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "社区名称")
    @NotBlank(message = "社区名称不能为空")
    private String comName;

    @ApiModelProperty(value = "社区联系人")
    private String comLinkUser;

    @ApiModelProperty(value = "社区联系人电话")
    private String comLinkPhone;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "状态")
    private Integer comState;

    @Override
    public String toString() {
        return "YwCommunity{" +
                "id='" + id + '\'' +
                ", comName='" + comName + '\'' +
                ", comLinkUser='" + comLinkUser + '\'' +
                ", comLinkPhone='" + comLinkPhone + '\'' +
                ", createTime=" + createTime +
                ", orderNum=" + orderNum +
                ", comState=" + comState +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComLinkUser() {
        return comLinkUser;
    }

    public void setComLinkUser(String comLinkUser) {
        this.comLinkUser = comLinkUser;
    }

    public String getComLinkPhone() {
        return comLinkPhone;
    }

    public void setComLinkPhone(String comLinkPhone) {
        this.comLinkPhone = comLinkPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getComState() {
        return comState;
    }

    public void setComState(Integer comState) {
        this.comState = comState;
    }
}
