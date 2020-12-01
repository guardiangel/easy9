package org.colin.utils;

import lombok.extern.slf4j.Slf4j;
import org.colin.model.dto.EmailNoticeDto;
import org.colin.model.ro.EmailRO;
import org.colin.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : TODO
 * @date : 2020-09-16 15:23:58
 */
@Component
@Slf4j
public class ThirdTool {

    @Resource
    private MailService mailService;

    @Value("${system.admin_email}")
    private String admin_email;

    @Value("${system.send_notice}")
    private String send_notice;

    @Resource
    private IPAddressTool ipAddressTool;

    @Value("${system.getIPAddress}")
    private String getIPAddress;//是否调用百度API接口获取IP归属地（0：不调用；1：调用）

    /**
     * @description: 发送注册时的验证码
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-16 15:29:02
     * @param: emailCode
     * @param: emailAddress
     * @return: void
     */
    @Async
    public void sendRegisterCode(String emailCode, String emailAddress){
        EmailRO ro = new EmailRO();
        ro.setObject("北方社区:注册验证码");
        StringBuffer emailContent = new StringBuffer("非常感谢您注册北方社区，欢迎来到我们这个大家庭，请自觉遵守社区协议。").append("<br/>");
        emailContent.append("您的注册验证码是：");
        emailContent.append(emailCode);
        emailContent.append("，5分钟内有效，请尽快完成注册，谢谢！").append("<br/>");
        emailContent.append("(如不是您本人操作，请忽略该邮件)").append("<br/>");
        emailContent.append("邮件发送时间：");
        emailContent.append(Tool.getCurrentTimeString());
        ro.setContent(emailContent.toString());
        ro.setTo(emailAddress);
        mailService.sendHtmlMail(ro);
    }

    /**
     * @description: 发送重置密码时的验证码
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-16 15:31:23
     * @param: emailCode
     * @param: emailAddress
     * @return: void
     */
    @Async
    public void sendResetPwdCode(String emailCode, String emailAddress){
        EmailRO ro = new EmailRO();
        ro.setObject("北方社区:重置密码验证码");
        StringBuffer emailContent = new StringBuffer("如不是您本人操作，请忽略该邮件").append("<br/>");
        emailContent.append("您的验证码是：");
        emailContent.append(emailCode);
        emailContent.append("，5分钟内有效，请尽快完成重置密码操作，谢谢！").append("<br/>");
        emailContent.append("邮件发送时间：");
        emailContent.append(Tool.getCurrentTimeString());
        ro.setContent(emailContent.toString());
        ro.setTo(emailAddress);
        mailService.sendHtmlMail(ro);
    }

    //发送普通邮件给管理员
    public void sendNoticeEmail(String userName, String eventDesc, String object, HttpServletRequest request){
        if ("1".equals(send_notice)) {
            EmailNoticeDto dto = new EmailNoticeDto();
            dto.setUserName(userName);
            String ip = IPUtils.getIpAddr(request);
            dto.setLoginIp(ip);
            String ipAddress = "";
            if ("1".equals(getIPAddress)) {
                //是否调用百度API接口获取IP归属地（0：不调用；1：调用）
                ipAddress = ipAddressTool.getAddressById(ip);
            }
            dto.setLoginAddress(ipAddress);
            dto.setEventDesc(eventDesc);
            dto.setLoginTime(DateUtils.getCurrentTimeByPattern());
            mailService.sendEmailByFreemarker(admin_email, object, dto, "loginNotice.html", "");
            log.info("发送通知邮件给管理员:{}", dto.toString());
        }else{
            log.info("配置：不发送通知给管理员");
        }
    }

    //发送普通邮件给管理员
    public void sendNoticeEmail(String object, String content){
        if ("1".equals(send_notice)) {
            EmailRO emailRO = new EmailRO();
            emailRO.setTo(admin_email);
            emailRO.setObject(object);
            emailRO.setContent(content);
            mailService.sendSimpleMail(emailRO);
            log.info("发送通知邮件给管理员:{}", emailRO.toString());
        }else{
            log.info("配置：不发送通知给管理员");
        }
    }
}
