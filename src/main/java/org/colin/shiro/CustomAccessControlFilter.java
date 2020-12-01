package org.colin.shiro;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.utils.DataResult;
import org.colin.utils.HttpContextUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
/**
 * @Description: CustomAccessControlFilter
 * @ClassName: CustomAccessControlFilter
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:53
 * @Version: 1.1.0
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            Subject subject = getSubject(servletRequest, servletResponse);
            log.debug("subject.isAuthenticated()={}", subject.isAuthenticated());
            log.debug("HttpContextUtils.isAjaxRequest(request)={}", HttpContextUtils.isAjaxRequest(request));
            log.debug("request.getMethod()={}" + request.getMethod());
            log.debug("request.getRequestURL().toString()={}" + request.getRequestURL().toString());
            String token = request.getHeader(Constant.ACCESS_TOKEN);
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException(BaseResponseCode.TOKEN_PARSE_ERROR);
            }
            CustomPasswordToken customPasswordToken = new CustomPasswordToken(token);
            getSubject(servletRequest, servletResponse).login(customPasswordToken);
        } catch (ServiceException exception) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                customRsponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
            } else if (exception.getMessageCode() == BaseResponseCode.TOKEN_ERROR.getCode()) {
                servletRequest.getRequestDispatcher("/index/login").forward(servletRequest, servletResponse);
            } else if (exception.getMessageCode() == BaseResponseCode.UNAUTHORIZED_ERROR.getCode()) {
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest, servletResponse);
            }
            return false;
        } catch (AuthenticationException e) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                if (e.getCause() instanceof ServiceException) {
                    ServiceException exception = (ServiceException) e.getCause();
                    customRsponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
                } else {
                    customRsponse(BaseResponseCode.SYSTEM_BUSY.getCode(), BaseResponseCode.SYSTEM_BUSY.getMsg(), servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest, servletResponse);
            }
            return false;
        } catch (Exception e) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                if (e.getCause() instanceof ServiceException) {
                    ServiceException exception = (ServiceException) e.getCause();
                    customRsponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
                } else {
                    customRsponse(BaseResponseCode.SYSTEM_BUSY.getCode(), BaseResponseCode.SYSTEM_BUSY.getMsg(), servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest, servletResponse);
            }
            return false;
        }
        return true;
    }

    private void customRsponse(int code, String msg, ServletResponse response) {
        try {
            DataResult result = DataResult.getResult(code, msg);
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String userJson = JSON.toJSONString(result);
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            log.error("eror={}", e);
        }
    }
}
