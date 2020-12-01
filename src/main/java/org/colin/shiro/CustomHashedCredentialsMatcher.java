package org.colin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.service.RedisService;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
/**
 * @Description: CustomHashedCredentialsMatcher
 * @ClassName: CustomHashedCredentialsMatcher
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:58
 * @Version: 1.1.0
 */
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomPasswordToken customPasswordToken = (CustomPasswordToken) token;
        String accessToken = (String) customPasswordToken.getPrincipal();
        String userId = JwtTokenUtil.getUserId(accessToken);
        if (redisService.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)) {
            throw new ServiceException(BaseResponseCode.ACCOUNT_LOCK_ERROR);
        }
//        if (redisService.hasKey(Constant.DELETED_USER_KEY + userId)) {
//            throw new ServiceException(BaseResponseCode.ACCOUNT_ERROR);
//        }
        if (redisService.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST + accessToken)) {
            throw new ServiceException(BaseResponseCode.TOKEN_ERROR);
        }
        if (redisService.hasKey(Constant.JWT_REFRESH_STATUS + accessToken)) {
            return true;
        }
        if (JwtTokenUtil.isTokenExpired(accessToken)) {
            throw new ServiceException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        if (redisService.hasKey(Constant.JWT_REFRESH_KEY + userId) && redisService.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS) > JwtTokenUtil.getRemainingTime(accessToken)) {
            if (!redisService.hasKey(Constant.JWT_REFRESH_IDENTIFICATION + accessToken)) {
                throw new ServiceException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
