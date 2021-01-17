package com.kkngai.blogjava.shiro;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : kkngai
 * @created : 14/1/2021, 星期四
 **/
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {
    private final String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
