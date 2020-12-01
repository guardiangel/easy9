package org.colin.exception;

import org.colin.code.ResponseCodeInterface;
/**
 * @Description: 通用服务异常
 * @ClassName: ServiceException
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 16:55
 * @Version: 1.1.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7158044853556609841L;

    /**
     * 异常编号
     */
    private final int messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private final String detailMessage;

    public ServiceException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public ServiceException(ResponseCodeInterface code) {
        this(code.getCode(), code.getMsg());
    }

    public ServiceException(String msg) {
        this(-000001, msg);
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
