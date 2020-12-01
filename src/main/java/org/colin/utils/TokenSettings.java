package org.colin.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

/**
 * @Description: TokenSettings
 * @ClassName: TokenSettings
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:32
 * @Version: 1.1.0
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenSettings {
    private String secretKey;
    private Duration accessTokenExpireTime;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String  issuer;
}
