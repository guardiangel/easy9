package org.colin.shiro;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.colin.constants.Constant;
import org.colin.service.PermissionService;
import org.colin.service.RedisService;
import org.colin.service.RoleService;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @Description: CustomRealm
 * @ClassName: CustomRealm
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:57
 * @Version: 1.1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private RedisService redisService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomPasswordToken;
    }

    /**
     * @Description: 授权
     * @MethodName: doGetAuthorizationInfo
     * @param:  [principalCollection]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: wujiangbo
     * @Date: 2020/2/19 12:16
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String accessToken = (String) SecurityUtils.getSubject().getPrincipal();
        String userId = JwtTokenUtil.getUserId(accessToken);
        log.info("userId={}", userId);
        if (redisService.hasKey(Constant.JWT_REFRESH_KEY + userId) && redisService.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS) > JwtTokenUtil.getRemainingTime(accessToken)) {
            List<String> roleNames = roleService.getRoleNames(userId);
            if (roleNames != null && !roleNames.isEmpty()) {
                authorizationInfo.addRoles(roleService.getRoleNames(userId));
            }
            authorizationInfo.setStringPermissions(permissionService.getPermissionsByUserId(userId));
        } else {
            Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
            if (claimsFromToken.get(Constant.JWT_ROLES_KEY) != null) {
                authorizationInfo.addRoles((Collection<String>) claimsFromToken.get(Constant.JWT_ROLES_KEY));
            }
            if (claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY) != null) {
                authorizationInfo.addStringPermissions((Collection<String>) claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY));
            }

        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomPasswordToken token = (CustomPasswordToken) authenticationToken;
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(), token.getPrincipal(), getName());
        return simpleAuthenticationInfo;
    }
}
