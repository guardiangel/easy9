package org.colin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * @Description: CustomPasswordToken
 * @ClassName: CustomPasswordToken
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:56
 * @Version: 1.1.0
 */
public class CustomPasswordToken extends UsernamePasswordToken {

    private String token;

    public CustomPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
