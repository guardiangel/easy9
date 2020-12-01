package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @ClassName: FlwCheckInfoRespVO
 * @Author: wujiangbo
 * @Date: 2020/8/4 0004 14:18
 * @Version: 1.1.0
 */
public class FlwCheckInfoRespVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "审核编码")
    private String checkCode;

    @ApiModelProperty(value = "审核人ID")
    private String userId;

    @ApiModelProperty(value = "审核人真实姓名")
    private String userRealName;

    @ApiModelProperty(value = "审核时间")
    private Date checkTime;

    @ApiModelProperty(value = "审核结果(0:审核不通过;1:审核中;2:审核通过;)")
    private String checkResult;

    @ApiModelProperty(value = "审核意见")
    private String checkOpinion;

    @ApiModelProperty(value = "审核顺序号")
    private String checkOrder;

    @Override
    public String toString() {
        return "FlwCheckInfoRespVO{" +
                "id='" + id + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", checkTime=" + checkTime +
                ", checkResult='" + checkResult + '\'' +
                ", checkOpinion='" + checkOpinion + '\'' +
                ", checkOrder='" + checkOrder + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(String checkOrder) {
        this.checkOrder = checkOrder;
    }
}
