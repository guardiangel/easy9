package org.colin.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.service.RedisService;
import org.colin.utils.DataResult;
import org.colin.utils.IPUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : 对URL和IP请求做验证，防止恶意刷新和暴力请求
 * @date : 2020-09-18 14:38:00
 */
@Slf4j
public class UrlIpInterceptor implements HandlerInterceptor {

    /**
     * 恶意刷新和暴力请求的IP被加入黑名单小时数
     */
    @Value("${system.ipBlackHours}")
    private long ipBlackHours;

    @Resource
    private RedisService redisService;

    private long maxAccessCount = 5;//5次

    private long existSeconds = 1;//1秒内

    private String BAD_IP_KEY = "interceptor_blcak_ip_key:";//黑名单IP存Redis的key

    private String URI_IP_COUNT = "interceptor_uri_ip_count_key:";//存URI+IP的次数到Redis的key

    public UrlIpInterceptor(){
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求是否属于方法的请求
        if (handler instanceof HandlerMethod) {
            String ip = IPUtils.getIpAddr(request);
            String uri = request.getRequestURI();
            /**
             * 判断逻辑：
             *     1秒内，同一IP访问同一接口次数超过5次，就将该IP置为黑名单72小时
             */
            Object blackIPTemp = redisService.get(BAD_IP_KEY + ip);//从redis中获取黑名单IP
            String blackIP = blackIPTemp == null ? "" : (String)blackIPTemp;
            if(!Tool.isBlank(blackIP)){
                //IP存在于黑名单中
                responseMessage(response, BaseResponseCode.BLACK_IP);
                return false;
            }else{
                //IP不在黑名单中，开始验证频率
                String uri_ip = uri + "_" + ip;
//                log.info("ip={};uri={}", ip, uri);
                Object uri_ip_count_temp = redisService.get(URI_IP_COUNT + uri_ip);
                long uri_ip_count = uri_ip_count_temp == null ? 0 : Long.valueOf((String)uri_ip_count_temp);
                if(uri_ip_count > maxAccessCount){
                    //将IP加入黑名单
                    log.info("IP[{}]被加入黑名单", ip);
//                    redisService.set(BAD_IP_KEY + ip, ip, 5, TimeUnit.SECONDS);//测试代码
                    redisService.set(BAD_IP_KEY + ip, ip, ipBlackHours, TimeUnit.HOURS);
                    //访问次数超过指定次数
                    responseMessage(response, BaseResponseCode.OPER_SO_FAST);
                    return false;
                }else{
                    //访问次数+1
                    uri_ip_count ++;
                    redisService.set(URI_IP_COUNT + uri_ip, uri_ip_count, existSeconds, TimeUnit.SECONDS);
                    return true;
                }
            }
        }
        return true;
    }

    //返回信息
    private void responseMessage(HttpServletResponse response, BaseResponseCode code) {
        try {
            response.setContentType("application/json;charset=" + Constant.SYSTEM_CHARACTER_ENCODING);
            OutputStream out = response.getOutputStream();
            DataResult result = new DataResult();
            result.setCode(code.getCode());
            result.setMsg(code.getMsg());
            String str = JSON.toJSONString(result);
            out.write(str.getBytes(Constant.SYSTEM_CHARACTER_ENCODING));
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("responseMessage error:{}", e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
