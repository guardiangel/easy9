package org.colin.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Description: 用于校验授权信息
 * @ClassName: CheckLicense
 * @Author: wujiangbo
 * @Date: 2020/7/10 0010 9:40
 * @Version: 1.1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLicense {

    String[] vertifys() default{};
}
