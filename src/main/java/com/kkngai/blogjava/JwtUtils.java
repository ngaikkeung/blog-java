package com.kkngai.blogjava;

import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : kkngai
 * @created : 14/1/2021, 星期四
 **/
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    public String generateToken(long useId) {
        return null;
    }

    public Claims getClaimByToken(String token) {
        return null;
    }

    public boolean isTokenExpired(Date expiration) {
        return  expiration.before(new Date());
    }
}
