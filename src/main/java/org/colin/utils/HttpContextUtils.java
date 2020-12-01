package org.colin.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 * @Description: http上下文
 * @ClassName: HttpContextUtils
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:54
 * @Version: 1.1.0
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /*
     * @Description: 判断是否是 ajax/app请求
     * @MethodName: isAjaxRequest
     * @param:  [request]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/2/17 10:54
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.indexOf("application/json") != -1
                || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        ));
    }
}
