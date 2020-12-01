package org.colin.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.model.entity.SysUser;
import org.colin.service.UserService;
import org.colin.utils.JwtTokenUtil;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: AOP切面实现登录验证
 * @ClassName: LoginCheckAspect
 * @Author: wujiangbo
 * @Date: 2020/6/23 0023 10:32
 * @Version: 1.1.0
 */
@Component
@Aspect
@Slf4j
public class LoginCheckAspect {

    @Autowired
    private UserService userService;

    /**
     * 此处的切点是注解的方式
     */
    @Pointcut("@annotation(org.colin.aop.annotation.LoginCheck)")
    public void pointCut() {
    }

    /*
     * @Author: wujiangbo
     * @Date: 2020/6/23 0023 11:09
     */
    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String access_token = request.getHeader(Constant.ACCESS_TOKEN);//获取前端传来的token
        log.info("北方社区-前端传入access_token={}", access_token);
        String desc = "未登录，请先登录！";
        if(Tool.isBlank(access_token) || "undefined".equalsIgnoreCase(access_token)){
            throw new ServiceException(9999, desc);
        }
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        if(Tool.isBlank(user_id)){
            throw new ServiceException(9999, desc);
        }else{
            SysUser user = userService.detailInfo(user_id);
            if(user == null){
                throw new ServiceException(9999, desc);
            }
            log.info("北方社区-用户[{}]登录校验成功", user.getRealName());
        }
    }
}
