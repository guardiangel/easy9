package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @ClassName: FlwRespVO
 * @Author: wujiangbo
 * @Date: 2020/8/4 0004 13:52
 * @Version: 1.1.0
 */
public class FlwRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "流程申请编号")
    private String flwCode;

    @ApiModelProperty(value = "申请时间")
    private Date createTime;

    @ApiModelProperty(value = "申请人")
    private String createUserId;

    @ApiModelProperty(value = "申请人姓名")
    private String createUserName;

    @ApiModelProperty(value = "流程类型")
    private String flwType;

    @ApiModelProperty(value = "流程类型名称")
    private String flwTypeName;

    @ApiModelProperty(value = "申请描述")
    private String descStr;

    @ApiModelProperty(value = "审核编码")
    private String checkCode;

    @ApiModelProperty(value = "最终审核结果(0:审核不通过;1:审核中;2:审核通过;3:申请作废;)")
    private String checkLastResult;

    @ApiModelProperty(value = "当前审核人ID")
    private String currentCheckUserId;

    @ApiModelProperty(value = "当前审核人姓名")
    private String currentCheckUserName;

    @Override
    public String toString() {
        return "FlwRespVO{" +
                "id='" + id + '\'' +
                ", flwCode='" + flwCode + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", flwType='" + flwType + '\'' +
                ", flwTypeName='" + flwTypeName + '\'' +
                ", descStr='" + descStr + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", checkLastResult='" + checkLastResult + '\'' +
                ", currentCheckUserId='" + currentCheckUserId + '\'' +
                ", currentCheckUserName='" + currentCheckUserName + '\'' +
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getFlwType() {
        return flwType;
    }

    public void setFlwType(String flwType) {
        this.flwType = flwType;
    }

    public String getFlwTypeName() {
        return flwTypeName;
    }

    public void setFlwTypeName(String flwTypeName) {
        this.flwTypeName = flwTypeName;
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

    public String getCurrentCheckUserName() {
        return currentCheckUserName;
    }

    public void setCurrentCheckUserName(String currentCheckUserName) {
        this.currentCheckUserName = currentCheckUserName;
    }
}
