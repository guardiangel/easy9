package org.colin.config;

import org.colin.interceptor.LicenseCheckInterceptor;
import org.colin.interceptor.UrlIpInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器配置类
 * @ClassName: InterceptorConfig
 * @Author: wujiangbo
 * @Date: 2020/7/10
 * @Version: 1.1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Bean
    public LicenseCheckInterceptor getLicenseCheckInterceptor() {
        return new LicenseCheckInterceptor();
    }

    @Bean
    public UrlIpInterceptor getUrlIpInterceptor() {
        return new UrlIpInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getUrlIpInterceptor()).addPathPatterns(new String[]{"/**"});
        registry.addInterceptor(this.getLicenseCheckInterceptor()).addPathPatterns(new String[]{"/**"});
    }
}
