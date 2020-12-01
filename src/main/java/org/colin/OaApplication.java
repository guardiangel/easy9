package org.colin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 表示开启AOP代理自动配置，如果配@EnableAspectJAutoProxy表示使用cglib进行代理对象的生成；
 * 设置@EnableAspectJAutoProxy(exposeProxy=true)表示通过aop框架暴露该代理对象
 * 启动类
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }

}
