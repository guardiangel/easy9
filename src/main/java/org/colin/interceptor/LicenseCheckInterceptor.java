package org.colin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.colin.aop.annotation.CheckLicense;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.license.LicenseVerify;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.springframework.web.method.HandlerMethod;

/**
 * @Description: 拦截器
 * @ClassName: LicenseCheckInterceptor
 * @Author: wujiangbo
 * @Date: 2020/7/10 0010 9:39
 * @Version: 1.1.0
 */
@Slf4j
public class LicenseCheckInterceptor implements HandlerInterceptor {

    /**
     * 是否验证授权(0：不验证；1：验证)
     */
    @Value("${license.checkLicenseFlag}")
    private String checkLicenseFlag;

    public LicenseCheckInterceptor(){
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("1".equals(checkLicenseFlag)){
            //判断请求是否属于方法的请求
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                CheckLicense annotation = method.getAnnotation(CheckLicense.class);
                if (annotation != null) {
                    LicenseVerify licenseVerify = new LicenseVerify();
                    //校验证书是否有效
                    boolean verifyResult = licenseVerify.verify();
                    if (verifyResult) {
                        log.info("License证书校验成功！");
                        return true;
                    } else {
                        throw new ServiceException(BaseResponseCode.LICENSE_ERROR);
                    }
                }
            }
        }
        return true;
    }
}
