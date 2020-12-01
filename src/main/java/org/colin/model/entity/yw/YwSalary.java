package org.colin.model.entity.yw;

import java.math.BigDecimal;
import java.util.Date;

public class YwSalary {

    private String id;

    private Date createTime;

    private String createUserId;

    private String deptId;

    private String userId;

    private String salaryYear;

    private String salaryMonth;

    private BigDecimal monthSalary;

    private BigDecimal bonus;

    private BigDecimal travelAllowance;

    private BigDecimal deptAchievements;

    private BigDecimal quarterAchievements;

    private BigDecimal specialSubsidy;

    private BigDecimal attendanceSubsidy;

    private BigDecimal alltimeBonus;

    private BigDecimal workyearBonus;

    private BigDecimal yanglaoInsurance;

    private BigDecimal yiliaoInsurance;

    private BigDecimal shiyeInsurance;

    private BigDecimal gongshangInsurance;

    private BigDecimal shengyuInsurance;

    private BigDecimal publicAccumulationFunds;

    private BigDecimal payTaxes;

    private BigDecimal punish;

    private BigDecimal attendancePunish;

    @Override
    public String toString() {
        return "YwSalary{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", userId='" + userId + '\'' +
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
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}