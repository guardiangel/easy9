package org.colin.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Description: 过滤器
 * @ClassName: LoginFilter
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/29 12:33
 * @Version: 1.1.0
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "LoginFilter")
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = crossFilter(servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    /**
     * @param arg1
     * @return
     * @desc 解决跨域请求问题
     * @author wujiangbo
     * @date 2019年11月20日 下午4:12:13
     */
    private HttpServletResponse crossFilter(ServletResponse arg1) {
        HttpServletResponse response = (HttpServletResponse) arg1;
        // 指示的请求的响应是否可以暴露于该页面。当true值返回时它可以被暴露
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 响应标头指定 指定可以访问资源的URI路径
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应标头指定响应访问所述资源到时允许的一种或多种方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        // 设置 缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "3600");
        // 设置 受支持请求标头
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,Authorization,Accept,User-Agent,Referer,Token,Area,authorization,content-type,refresh_token");
        return response;
    }
}
