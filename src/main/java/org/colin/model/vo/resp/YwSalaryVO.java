package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @ClassName: YwSalaryVO
 * @Author: wujiangbo
 * @Date: 2020/7/13 20:28
 * @Version: 1.1.0
 */
public class YwSalaryVO {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserIdName;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptIdName;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "用户真实姓名")
    private String userIdName;

    @ApiModelProperty(value = "工资发放年份")
    private String salaryYear;

    @ApiModelProperty(value = "工资发放月份")
    private String salaryMonth;

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

    @ApiModelProperty(value = "总扣款")
    private BigDecimal total_koukuan;

    @ApiModelProperty(value = "总奖金")
    private BigDecimal total_jiangjin;

    @ApiModelProperty(value = "实发工资")
    private BigDecimal actual_pay;

    @ApiModelProperty(value = "发放时间")
    private String pay_time;

    @Override
    public String toString() {
        return "YwSalaryVO{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", createUserIdName='" + createUserIdName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptIdName='" + deptIdName + '\'' +
                ", userId='" + userId + '\'' +
                ", userIdName='" + userIdName + '\'' +
                ", salaryYear='" + salaryYear + '\'' +
                ", salaryMonth='" + salaryMonth + '\'' +
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
                ", total_koukuan=" + total_koukuan +
                ", total_jiangjin=" + total_jiangjin +
                ", actual_pay=" + actual_pay +
                ", pay_time='" + pay_time + '\'' +
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

    public String getCreateUserIdName() {
        return createUserIdName;
    }

    public void setCreateUserIdName(String createUserIdName) {
        this.createUserIdName = createUserIdName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptIdName() {
        return deptIdName;
    }

    public void setDeptIdName(String deptIdName) {
        this.deptIdName = deptIdName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdName() {
        return userIdName;
    }

    public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
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

    public BigDecimal getTotal_koukuan() {
        return total_koukuan;
    }

    public void setTotal_koukuan(BigDecimal total_koukuan) {
        this.total_koukuan = total_koukuan;
    }

    public BigDecimal getTotal_jiangjin() {
        return total_jiangjin;
    }

    public void setTotal_jiangjin(BigDecimal total_jiangjin) {
        this.total_jiangjin = total_jiangjin;
    }

    public BigDecimal getActual_pay() {
        return actual_pay;
    }

    public void setActual_pay(BigDecimal actual_pay) {
        this.actual_pay = actual_pay;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }
}
