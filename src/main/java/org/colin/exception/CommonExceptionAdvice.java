package org.colin.exception;

import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import io.lettuce.core.RedisConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.UnexpectedTypeException;
import java.util.Map;
/**
 * @Description: 异常处理类
 * @ClassName: CommonExceptionAdvice
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 11:20
 * @Version: 1.1.0
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class CommonExceptionAdvice extends CommonHandel {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    /**
     * 通用自定义异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public Map<String, Object> ServiceException(ServiceException e) {
        log.error("通用自定义异常:{}", e.getMessage());
        if(log.isDebugEnabled()){
            log.debug("通用自定义异常：", e);
        }
        return this.error(e.getMessageCode(), e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        if(log.isDebugEnabled()){
            log.debug("参数验证失败", e);
        }
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String defaultMessage = error.getDefaultMessage();
        String errorMsg = "";
        if(!Tool.isBlank(defaultMessage)){
            if(defaultMessage.contains(":")){
                errorMsg = defaultMessage.substring(defaultMessage.lastIndexOf(":") + 1, defaultMessage.length());
            }else{
                errorMsg = defaultMessage;
            }
        }
        log.error("参数验证失败:{}", errorMsg);
        return this.error(1001, errorMsg);
    }

    /**
     * 文件上传大小超出限制
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map<String, Object> MaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("上传文件大小超出限制:", e);
        String msg = "上传单个文件最大大小为：" + maxFileSize + "，单次上传总文件最大大小为：" + maxRequestSize;
        return this.error(1002, msg);
    }

    /**
     * 插入数据库值过大
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(PacketTooBigException.class)
    public Map<String, Object> PacketTooBigException(PacketTooBigException e) {
        log.error("插入数据库值过大:", e);
        return this.error(1003, "插入数据库值过大");
    }

    /**
     * Redis服务器连接不上异常：500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RedisConnectionException.class)
    public Map<String, Object> RedisConnectionException(RedisConnectionException e) {
        log.error("Redis服务器连接异常:", e);
        return this.error(1004, "Redis服务器连接异常");
    }

    /**
     * 无操作权限-1005
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoPermissionException.class)
    public Map<String, Object> NoPermissionException(NoPermissionException e) {
        log.error("无操作权限:", e);
        return this.error(1005, e.getMessage());
    }

    /**
     * 入参校验失败异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({UnexpectedTypeException.class})
    public Map<String, Object> FiledValidationFailed1(UnexpectedTypeException e) {
        String errorMsg = "";
        if(!Tool.isBlank(e.getMessage())){
            String str = e.getMessage();
            if(str.contains(":")){
                errorMsg = str.substring(str.lastIndexOf(":") + 1, str.length());
            }else{
                errorMsg = str;
            }
        }
        return this.error(1006, errorMsg);
    }
}
