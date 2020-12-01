package org.colin.utils;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * @Description:
 * @ClassName: RedisCheck
 * @Author: wujiangbo
 * @Date: 2020/6/29
 * @Version: 1.1.0
 */
@Component
public class RedisCheck {

    @Autowired
    private RedisService redisService;

    /*
     * @Description: 操作频率验证
     * @MethodName: operFrequencyCheck
     * @param:  [redis_key, redis_value, time, unit]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/6/29 0029 11:30
     */
    public void operFrequencyCheck(String redis_key, String redis_value, long time, TimeUnit unit, String error_msg) {
        //操作频率验证
        Object redisContent = redisService.get(redis_key);
        if(redisContent == null){
            redisService.set(redis_key, redis_value, time, unit);
        }else{
            if(Tool.isBlank(error_msg)){
                //抛错，操作频繁
                throw new ServiceException(BaseResponseCode.OPER_SO_FAST);
            }else{
                throw new ServiceException(error_msg);
            }
        }
    }
}
