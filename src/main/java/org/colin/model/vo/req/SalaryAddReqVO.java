package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: wujiangbo(QQ:1134135987)
 * @Date: 2020/07/14 10:32:33
 */
public class SalaryAddReqVO {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

    @ApiModelProperty(value = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(value = "工资发放年份")
    private String salaryYear;

    @ApiModelProperty(value = "工资发放月份")
    private String salaryMonth;

    @ApiModelProperty(value = "年份-月份")
    private String yearMonth;

    @ApiModelProperty(value = "月薪(税前)")
    private BigDecimal monthSalary;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "出差补助")
    private BigDecimal travelAllowance;

    @ApiModelProperty(value = "部门绩效奖金")
    private BigDecimal deptAchievements;

    @ApiModelProperty(value = "季度绩效奖金")
    private BigDecimal quarterAchievements;

    @ApiModelProperty(value = "特殊补贴")
    private BigDecimal specialSubsidy;

    @ApiModelProperty(value = "考勤补贴")
    private BigDecimal attendanceSubsidy;

    @ApiModelProperty(value = "全勤奖")
    private BigDecimal alltimeBonus;

    @ApiModelProperty(value = "工龄奖")
    private BigDecimal workyearBonus;

    @ApiModelProperty(value = "养老保险")
    private BigDecimal yanglaoInsurance;

    @ApiModelProperty(value = "医疗保险")
    private BigDecimal yiliaoInsurance;

    @ApiModelProperty(value = "失业保险")
    private BigDecimal shiyeInsurance;

    @ApiModelProperty(value = "工伤保险")
    private BigDecimal gongshangInsurance;

    @ApiModelProperty(value = "生育保险")
    private BigDecimal shengyuInsurance;

    @ApiModelProperty(value = "公积金")
    private BigDecimal publicAccumulationFunds;

    @ApiModelProperty(value = "纳税总金额")
    private BigDecimal payTaxes;

    @ApiModelProperty(value = "罚款")
    private BigDecimal punish;

    @ApiModelProperty(value = "考勤扣款")
    private BigDecimal attendancePunish;

    @Override
    public String toString() {
        return "SalaryAddReqVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", userId='" + userId + '\'' +
                ", salaryYear='" + salaryYear + '\'' +
                ", salaryMonth='" + salaryMonth + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", monthSalary=" + monthSalary +
                ", bonus=" + bonus +
                ", travelAllowance=" + travelAllowance +
                ", deptAchievements=" + deptAchievements +
                ", quarterAchievements=" + quarterAchievements +
                ", specialSubsidy=" + specialSubsidy +
                ", attendanceSubsidy=" + attendanceSubsidy +
                ", alltimeBonus=" + alltimeBonus +
                ", workyearBonus=" + workyearBonus +
                ", yanglaoInsurance=" + yanglaoInsurance +
                ", yiliaoInsurance=" + yiliaoInsurance +
                ", shiyeInsurance=" + shiyeInsurance +
                ", gongshangInsurance=" + gongshangInsurance +
                ", shengyuInsurance=" + shengyuInsurance +
                ", publicAccumulationFunds=" + publicAccumulationFunds +
                ", payTaxes=" + payTaxes +
                ", punish=" + punish +
                ", attendancePunish=" + attendancePunish +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalaryYear() {
        return salaryYear;
    }

    public void setSalaryYear(String salaryYear) {
        this.salaryYear = salaryYear;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(BigDecimal monthSalary) {
        this.monthSalary = monthSalary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getTravelAllowance() {
        return travelAllowance;
    }

    public void setTravelAllowance(BigDecimal travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public BigDecimal getDeptAchievements() {
        return deptAchievements;
    }

    public void setDeptAchievements(BigDecimal deptAchievements) {
        this.deptAchievements = deptAchievements;
    }

    public BigDecimal getQuarterAchievements() {
        return quarterAchievements;
    }

    public void setQuarterAchievements(BigDecimal quarterAchievements) {
        this.quarterAchievements = quarterAchievements;
    }

    public BigDecimal getSpecialSubsidy() {
        return specialSubsidy;
    }

    public void setSpecialSubsidy(BigDecimal specialSubsidy) {
        this.specialSubsidy = specialSubsidy;
    }

    public BigDecimal getAttendanceSubsidy() {
        return attendanceSubsidy;
    }

    public void setAttendanceSubsidy(BigDecimal attendanceSubsidy) {
        this.attendanceSubsidy = attendanceSubsidy;
    }

    public BigDecimal getAlltimeBonus() {
        return alltimeBonus;
    }

    public void setAlltimeBonus(BigDecimal alltimeBonus) {
        this.alltimeBonus = alltimeBonus;
    }

    public BigDecimal getWorkyearBonus() {
        return workyearBonus;
    }

    public void setWorkyearBonus(BigDecimal workyearBonus) {
        this.workyearBonus = workyearBonus;
    }

    public BigDecimal getYanglaoInsurance() {
        return yanglaoInsurance;
    }

    public void setYanglaoInsurance(BigDecimal yanglaoInsurance) {
        this.yanglaoInsurance = yanglaoInsurance;
    }

    public BigDecimal getYiliaoInsurance() {
        return yiliaoInsurance;
    }

    public void setYiliaoInsurance(BigDecimal yiliaoInsurance) {
        this.yiliaoInsurance = yiliaoInsurance;
    }

    public BigDecimal getShiyeInsurance() {
        return shiyeInsurance;
    }

    public void setShiyeInsurance(BigDecimal shiyeInsurance) {
        this.shiyeInsurance = shiyeInsurance;
    }

    public BigDecimal getGongshangInsurance() {
        return gongshangInsurance;
    }

    public void setGongshangInsurance(BigDecimal gongshangInsurance) {
        this.gongshangInsurance = gongshangInsurance;
    }

    public BigDecimal getShengyuInsurance() {
        return shengyuInsurance;
    }

    public void setShengyuInsurance(BigDecimal shengyuInsurance) {
        this.shengyuInsurance = shengyuInsurance;
    }

    public BigDecimal getPublicAccumulationFunds() {
        return publicAccumulationFunds;
    }

    public void setPublicAccumulationFunds(BigDecimal publicAccumulationFunds) {
        this.publicAccumulationFunds = publicAccumulationFunds;
    }

    public BigDecimal getPayTaxes() {
        return payTaxes;
    }

    public void setPayTaxes(BigDecimal payTaxes) {
        this.payTaxes = payTaxes;
    }

    public BigDecimal getPunish() {
        return punish;
    }

    public void setPunish(BigDecimal punish) {
        this.punish = punish;
    }

    public BigDecimal getAttendancePunish() {
        return attendancePunish;
    }

    public void setAttendancePunish(BigDecimal attendancePunish) {
        this.attendancePunish = attendancePunish;
    }
}
