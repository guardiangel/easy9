package org.colin.license;

import lombok.extern.slf4j.Slf4j;
import org.colin.license.pojo.LicenseVerifyParam;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description: 在项目启动时安装证书
 * @ClassName: LicenseCheckListener
 * @Author: wujiangbo
 * @Date: 2020/7/10 0010 14:04
 * @Version: 1.1.0
 */
@Component
@Slf4j
public class LicenseCheckListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 证书subject
     */
    @Value("${license.subject}")
    private String subject;

    /**
     * 公钥别称
     */
    @Value("${license.publicAlias}")
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    private String storePass;

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    @Value("${license.publicKeysStorePath}")
    private String publicKeysStorePath;

    /**
     * 是否验证授权(0：不验证；1：验证)
     */
    @Value("${license.checkLicenseFlag}")
    private String checkLicenseFlag;

    //在项目启动时安装证书
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if("1".equals(checkLicenseFlag)){
            ApplicationContext context = event.getApplicationContext().getParent();
            if(context == null){
                if(StringUtils.isNotBlank(licensePath)){
                    log.info("++++++++ 开始安装证书 ++++++++");
                    LicenseVerifyParam param = new LicenseVerifyParam();
                    param.setSubject(subject);
                    param.setPublicAlias(publicAlias);
                    param.setStorePass(storePass);
                    param.setLicensePath(licensePath);
                    param.setPublicKeysStorePath(publicKeysStorePath);
                    LicenseVerify licenseVerify = new LicenseVerify();
                    //安装证书
                    licenseVerify.install(param);
                    log.info("++++++++ 证书安装结束 ++++++++");
                }
            }
        }else{
            log.info("不进行License授权验证");
        }
    }
}
