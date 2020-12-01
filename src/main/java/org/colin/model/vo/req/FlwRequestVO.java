package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * @Description:
 * @ClassName: FlwRequestVO
 * @Author: wujiangbo
 * @Date: 2020/8/4 0004 13:49
 * @Version: 1.1.0
 */
public class FlwRequestVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "申请人ID")
    private String requestUserId;

    @ApiModelProperty(value = "流程类型")
    private String flwType;

    @ApiModelProperty(value = "最终审核结果(0:审核不通过;1:审核中;2:审核通过;)")
    private String checkLastResult;

    @Override
    public String toString() {
        return "FlwRequestVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", requestUserId='" + requestUserId + '\'' +
                ", flwType='" + flwType + '\'' +
                ", checkLastResult='" + checkLastResult + '\'' +
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

    public String getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
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
}
