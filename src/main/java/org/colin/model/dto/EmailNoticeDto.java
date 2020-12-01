package org.colin.model.dto;

//发送通知邮件给管理员的内容
public class EmailNoticeDto {

    private String userName;//用户姓名
    private String loginIp;//登录IP
    private String loginAddress;//登录IP地址
    private String loginTime;//登录时间
    private String eventDesc;//事件描述

    @Override
    public String toString() {
        return "EmailNoticeDto{" +
                "userName='" + userName + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginAddress='" + loginAddress + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", eventDesc='" + eventDesc + '\'' +
                '}';
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
