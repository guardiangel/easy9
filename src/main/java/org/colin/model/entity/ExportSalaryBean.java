package org.colin.model.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@ContentRowHeight(25)
@HeadRowHeight(36)
@ColumnWidth(16)
public class ExportSalaryBean extends BaseRowModel implements Serializable {

    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userIdName;

    @ExcelProperty(value = {"部门"}, index = 1)
    private String deptIdName;

    //忽略这个字段
    @ExcelIgnore
    private String salaryYear;

    //忽略这个字段
    @ExcelIgnore
    private String salaryMonth;

    @ExcelProperty(value = {"发放时间"}, index = 2)
    private String pay_time;

    @ExcelProperty(value = {"月薪(税前)"}, index = 3)
    private BigDecimal monthSalary;

    @ExcelProperty(value = {"奖金"}, index = 4)
    private BigDecimal bonus;

    @ExcelProperty(value = {"出差补助"}, index = 5)
    private BigDecimal travelAllowance;

    @ExcelProperty(value = {"部门绩效奖金"}, index = 6)
    private BigDecimal deptAchievements;

    @ExcelProperty(value = {"季度绩效奖金"}, index = 7)
    private BigDecimal quarterAchievements;

    @ExcelProperty(value = {"特殊补贴"}, index = 8)
    private BigDecimal specialSubsidy;

    @ExcelProperty(value = {"考勤补贴"}, index = 9)
    private BigDecimal attendanceSubsidy;

    @ExcelProperty(value = {"全勤奖"}, index = 10)
    private BigDecimal alltimeBonus;

    @ExcelProperty(value = {"工龄奖"}, index = 11)
    private BigDecimal workyearBonus;

    @ExcelProperty(value = {"养老保险"}, index = 12)
    private BigDecimal yanglaoInsurance;

    @ExcelProperty(value = {"医疗保险"}, index = 13)
    private BigDecimal yiliaoInsurance;

    @ExcelProperty(value = {"失业保险"}, index = 14)
    private BigDecimal shiyeInsurance;

    @ExcelProperty(value = {"工伤保险"}, index = 15)
    private BigDecimal gongshangInsurance;

    @ExcelProperty(value = {"生育保险"}, index = 16)
    private BigDecimal shengyuInsurance;

    @ExcelProperty(value = {"公积金"}, index = 17)
    private BigDecimal publicAccumulationFunds;

    @ExcelProperty(value = {"纳税"}, index = 18)
    private BigDecimal payTaxes;

    @ExcelProperty(value = {"罚款"}, index = 19)
    private BigDecimal punish;

    @ExcelProperty(value = {"考勤扣款"}, index = 20)
    private BigDecimal attendancePunish;

    @ExcelProperty(value = {"总扣款"}, index = 21)
    private BigDecimal total_koukuan;

    @ExcelProperty(value = {"总奖金"}, index = 22)
    private BigDecimal total_jiangjin;

    @ExcelProperty(value = {"实发工资"}, index = 23)
    private BigDecimal actual_pay;

    @Override
    public String toString() {
        return "ExportSalaryBean{" +
                "userIdName='" + userIdName + '\'' +
                ", deptIdName='" + deptIdName + '\'' +
                ", salaryYear='" + salaryYear + '\'' +
                ", salaryMonth='" + salaryMonth + '\'' +
                ", pay_time='" + pay_time + '\'' +
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
                '}';
    }

    public String getUserIdName() {
        return userIdName;
    }

    public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }

    public String getDeptIdName() {
        return deptIdName;
    }

    public void setDeptIdName(String deptIdName) {
        this.deptIdName = deptIdName;
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

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
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
}
