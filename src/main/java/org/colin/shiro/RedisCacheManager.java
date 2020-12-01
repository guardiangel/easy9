package org.colin.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.colin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: RedisCacheManager
 * @ClassName: RedisCacheManager
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:45
 * @Version: 1.1.0
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisService redisService;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache(name, redisService);
    }
}
