package org.colin.exception;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description: CommonHandel
 * @ClassName: CommonHandel
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 11:18
 * @Version: 1.1.0
 */
public class CommonHandel {

    protected Map<String, Object> success(int code, String msg, HashMap<String, Object> data) {
        return this.result(code, msg, data);
    }

    protected Map<String, Object> success(int code, String msg) {
        return this.result(code, msg);
    }

    protected Map<String, Object> error(int code, String msg, HashMap<String, Object> data) {
        return this.result(code, msg, data);
    }

    protected Map<String, Object> error(int code, String msg) {
        return this.result(code, msg);
    }

    private Map<String, Object> result(int code, String msg, HashMap<String, Object> data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    private Map<String, Object> result(int code, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", new HashMap<>());
        return result;
    }
}
