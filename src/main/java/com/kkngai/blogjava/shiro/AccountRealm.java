package com.kkngai.blogjava.shiro;


import cn.hutool.core.bean.BeanUtil;
import com.kkngai.blogjava.JwtUtils;
import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author : kkngai
 * @created : 14/1/2021, 星期四
 **/
@Slf4j
@Component
@AllArgsConstructor
public class AccountRealm extends AuthorizingRealm {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principas) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("JWT[{}]", jwt);

        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        Optional<User> optionalUser = userService.findById(Long.parseLong(userId));

        if (optionalUser.isEmpty()) {
            throw new UnknownAccountException("Account not exists");
        }
        if (optionalUser.get().getStatus() == -1) {
            throw  new LockedAccountException("Account has locked");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(optionalUser.get(), profile);

        log.info("Profile[{}]", profile.toString());

        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}
