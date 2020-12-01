package org.colin.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

public class YwFlwRequest implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "流程申请编号")
    private String flwCode;

    @ApiModelProperty(value = "申请时间")
    private Date createTime;

    @ApiModelProperty(value = "申请人")
    private String createUserId;

    @ApiModelProperty(value = "流程类型")
    private String flwType;

    @ApiModelProperty(value = "申请描述")
    private String descStr;

    @ApiModelProperty(value = "审核编码")
    private String checkCode;

    @ApiModelProperty(value = "最终审核结果(0:审核不通过;1:审核中;2:审核通过;3:申请作废;)")
    private String checkLastResult;

    @ApiModelProperty(value = "当前审核人ID")
    private String currentCheckUserId;

    @Override
    public String toString() {
        return "YwFlwRequest{" +
                "id='" + id + '\'' +
                ", flwCode='" + flwCode + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", flwType='" + flwType + '\'' +
                ", descStr='" + descStr + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", checkLastResult='" + checkLastResult + '\'' +
                ", currentCheckUserId='" + currentCheckUserId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlwCode() {
        return flwCode;
    }

    public void setFlwCode(String flwCode) {
        this.flwCode = flwCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getFlwType() {
        return flwType;
    }

    public void setFlwType(String flwType) {
        this.flwType = flwType;
    }

    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckLastResult() {
        return checkLastResult;
    }

    public void setCheckLastResult(String checkLastResult) {
        this.checkLastResult = checkLastResult;
    }

    public String getCurrentCheckUserId() {
        return currentCheckUserId;
    }

    public void setCurrentCheckUserId(String currentCheckUserId) {
        this.currentCheckUserId = currentCheckUserId;
    }
}