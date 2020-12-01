package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * @Description:
 * @ClassName: CheckRequestVO
 * @Author: wujiangbo
 * @Date: 2020/8/5 0005 20:42
 * @Version: 1.1.0
 */
public class CheckRequestVO implements Serializable {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "流程申请编号")
    private String flwCode;

    @ApiModelProperty(value = "审核人ID")
    private String checkUserId;

    @ApiModelProperty(value = "申请人姓名")
    private String requestName;

    @ApiModelProperty(value = "流程类型")
    private String flwType;

    @ApiModelProperty(value = "最终审核结果(0:审核不通过;1:审核中;2:审核通过;)")
    private String checkLastResult;

    @ApiModelProperty(value = "审核结果(审核结果(0:审核不通过;1:审核中;2:审核通过;))")
    private String checkResult;

    @ApiModelProperty(value = "审核编码")
    private String checkCode;

    @ApiModelProperty(value = "审核意见")
    private String checkOpinion;

    @Override
    public String toString() {
        return "CheckRequestVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", id='" + id + '\'' +
                ", flwCode='" + flwCode + '\'' +
                ", checkUserId='" + checkUserId + '\'' +
                ", requestName='" + requestName + '\'' +
                ", flwType='" + flwType + '\'' +
                ", checkLastResult='" + checkLastResult + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", checkOpinion='" + checkOpinion + '\'' +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getFlwType() {
        return flwType;
    }

    public void setFlwType(String flwType) {
        this.flwType = flwType;
    }

    public String getCheckLastResult() {
        return checkLastResult;
    }

    public void setCheckLastResult(String checkLastResult) {
        this.checkLastResult = checkLastResult;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }
}
