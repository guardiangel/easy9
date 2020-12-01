package org.colin.utils;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: IP地址工具类
 * @ClassName: IPUtils
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:40
 * @Version: 1.1.0
 */
@Slf4j
public class IPUtils {

    /**
     * @description: 根据HttpServletRequest判断访问者终端类型
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-18 10:45:07
     * @param: request
     * @return: java.lang.String
     */
    public static String getAccessType(HttpServletRequest request) {
        String returnString = "5";//访问方式（1：Web；2：H5；3：Android；4：IOS；5：其他；6：iPad；）
        try {
            String User_Agent = request.getHeader("User-Agent");
            if (User_Agent.contains("Android") || User_Agent.contains("Linux")) {
                returnString = "3";
                log.info("Android移动客户端访问");
                if (User_Agent.contains("MicroMessenger")) {
                    log.info("Android移动客户端访问-----微信");
                }
            } else if (User_Agent.contains("iPhone")) {
                returnString = "4";
                log.info("iPhone移动客户端访问");
                if (User_Agent.contains("MicroMessenger")) {
                    log.info("iPhone移动客户端访问-----微信");
                }
            } else if (User_Agent.contains("iPad")) {
                returnString = "5";
                log.info("iPad客户端访问");
                if (User_Agent.contains("MicroMessenger")) {
                    log.info("iPad客户端-----微信");
                }
            } else if(User_Agent.contains("Windows")){
                returnString = "1";
                log.info("Windows访问");
            }else{
                returnString = "5";
                log.info("其他访问");
            }
        } catch (Exception e) {
            log.error("获取[访问者终端类型]时发生异常:{}", e.getLocalizedMessage());
            return returnString;
        }
        return returnString;
    }

    //获取浏览器的User-Agent
    public static String getUserAgent(HttpServletRequest request) {
        String User_Agent = request.getHeader("User-Agent");
        log.info("User_Agent={}", User_Agent);
        return request.getHeader("User-Agent");
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("获取[IP地址]时发生异常:{}", e.getLocalizedMessage());
            return ip;
        }
        // 使用代理，则获取第一个IP地址
        if (!Tool.isBlank(ip) && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
