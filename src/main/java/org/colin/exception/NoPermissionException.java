package org.colin.exception;

/**
 * @Description: 无权限操作异常
 * @ClassName: NoPermissionException
 * @Author: wujiangbo
 * @Date: 2020/6/23 0023 10:33
 * @Version: 1.1.0
 */
public class NoPermissionException extends RuntimeException{

    private static final long serialVersionUID = -7385947547979865899L;

    public NoPermissionException(String msg) {
        super(msg);
    }
}
