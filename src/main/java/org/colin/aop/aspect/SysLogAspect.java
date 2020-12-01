package org.colin.aop.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.mapper.SysLogMapper;
import org.colin.model.entity.SysLog;
import org.colin.utils.HttpContextUtils;
import org.colin.utils.IPUtils;
import org.colin.utils.JwtTokenUtil;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description: SysLogAspect
 * @ClassName: SysLogAspect
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:33
 * @Version: 1.1.0
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 此处的切点是注解的方式
     * 只要出现 @LogAnnotation注解都会进入
     */
    @Pointcut("@annotation(org.colin.aop.annotation.LogAnnotation)")
    public void logPointCut() {
    }

    /**
     * 环绕增强,相当于MethodInterceptor
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        try {
            saveSysLog(point, time);
        } catch (Exception e) {
            log.error("e={}", e);
        }
        return result;
    }

    /*
     * @Description: 保存日志信息
     * @MethodName: saveSysLog
     * @param:  [joinPoint, time]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/2/17 20:39
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if (logAnnotation != null) {
            //注解上的描述
            sysLog.setOperation(logAnnotation.title() + "-" + logAnnotation.action());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        log.info("请求{}.{}耗时{}毫秒", className, methodName, time);
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            String params = null;
            if (args.length != 0) {
                params = JSON.toJSONString(args);
            }
            sysLog.setParams(params);
        } catch (Exception e) {
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        log.info("Ip{}，接口地址{}，请求方式{}，入参：{}", sysLog.getIp(), request.getRequestURL(), request.getMethod(), sysLog.getParams());
        //用户名
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        if(!Tool.isBlank(token)){
            String userId = JwtTokenUtil.getUserId(token);
            String username = JwtTokenUtil.getUserName(token);
            sysLog.setUsername(username);
            sysLog.setUserId(userId);
            sysLog.setTime((int) time);
            sysLog.setId(Tool.getPrimaryKey());
            sysLog.setCreateTime(new Date());
            log.info(sysLog.toString());
            sysLogMapper.insertSelective(sysLog);
        }
    }
}
