package org.colin.serializer;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description: MyStringRedisSerializer
 * @ClassName: MyStringRedisSerializer
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:51
 * @Version: 1.1.0
 */
public class MyStringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public MyStringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public MyStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        if (object instanceof String) {
            return object.toString().getBytes(charset);
        } else {
            String string = JSON.toJSONString(object);
            return string.getBytes(charset);
        }

    }
}