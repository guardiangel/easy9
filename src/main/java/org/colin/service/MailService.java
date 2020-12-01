package org.colin.service;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.colin.model.dto.EmailNoticeDto;
import org.colin.model.ro.EmailRO;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: MailService
 * @ClassName: MailService
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/29 18:07
 * @Version: 1.1.0
 */
@Component
@Slf4j
public class MailService {

    @Resource
    private JavaMailSender mailSender;

    //发送邮件的模板引擎
    @Resource
    private FreeMarkerConfigurer configurer;

    @Value("${spring.mail.username}")
    private String from;

    /*
     * @Description: 发送普通文本邮件
     * @MethodName: sendSimpleMail
     * @param:  [emailRO]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/2/29 18:13
     */
    @Async
    public void sendSimpleMail(EmailRO emailRO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailRO.getTo());
        message.setSubject(emailRO.getObject());
        message.setText(emailRO.getContent());
        try {
            mailSender.send(message);
            log.info("发送给[{}]的普通邮件成功！", emailRO.getTo());
        } catch (Exception e) {
            log.error("发送普通邮件失败：{}", e);
        }
    }

    /*
     * @Description: 发送Html内容邮件
     * @MethodName: sendHtmlMail
     * @param:  [emailRO]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/2/29 18:13
     */
    //<html><head></head><body><h3>哈哈，什么都没有</h3></body></html>
    @Async
    public void sendHtmlMail(EmailRO emailRO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            //true 表⽰示需要创建⼀一个 multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(emailRO.getTo());
            helper.setSubject(emailRO.getObject());
            helper.setText(emailRO.getContent(), true);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送HTML邮件失败：{}", e);
        }
    }

    /*
     * @Description: 发送带附件的邮件
     * @MethodName: sendAttachmentMail
     * @param:  [emailRO, filePath]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/2/29 18:12
     */
    @Async
    public void sendAttachmentMail(EmailRO emailRO, String filePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            //true 表⽰示需要创建⼀一个 multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(emailRO.getTo());
            helper.setSubject(emailRO.getObject());
            helper.setText(emailRO.getContent(), true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送带附件邮件失败：{}", e);
        }
    }

    //实现模板发送邮件
    //filePath:E:\Test\linchanglan.jpg
    @Async
    public void sendEmailByFreemarker(String to, String object, EmailNoticeDto dto, String templateName, String filePath) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(to);//接收者
            helper.setSubject(object);//邮件标题
            if(!Tool.isBlank(filePath)){
                //添加附件
                FileSystemResource file = new FileSystemResource(new File(filePath));
                helper.addAttachment("attachment" + filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()), file);
            }
            Map<String, Object> model = new HashMap<>();
            model.put("params", dto);
            Template template = configurer.getConfiguration().getTemplate(templateName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("发送模板邮件失败：{}", e);
        }
    }
}
