package org.colin.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Description: 登录检查注解（用于Controller接口的方法上，表示该接口需要进行登录验证）
 * @ClassName: LoginCheck
 * @Author: wujiangbo
 * @Date: 2020/6/23 0023 10:31
 * @Version: 1.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {

    //权限字符串(为空则不校验)
    String permission() default "";
}
