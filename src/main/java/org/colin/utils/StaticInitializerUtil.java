package org.colin.utils;

import org.springframework.stereotype.Component;
/**
 * @Description: StaticInitializerUtil
 * @ClassName: StaticInitializerUtil
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 11:13
 * @Version: 1.1.0
 */
@Component
public class StaticInitializerUtil {

    public StaticInitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
