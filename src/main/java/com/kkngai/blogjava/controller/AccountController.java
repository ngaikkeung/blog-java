package com.kkngai.blogjava.controller;

import cn.hutool.crypto.SecureUtil;
import com.kkngai.blogjava.JwtUtils;
import com.kkngai.blogjava.common.lang.Result;
import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@Validated @RequestBody User user, HttpServletResponse httpServletResponse) {
        Optional<User> foundUser = userService.findByUserName(user.getUsername());

        if (foundUser.isEmpty()) {
            throw new UnauthenticatedException("User not exists");
        }

       if (!foundUser.get().getPassword().equals(SecureUtil.md5(user.getPassword()))) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.fail("Invalid password"));
       }

       String jwt = jwtUtils.generateToken(foundUser.get().getId());
       httpServletResponse.setHeader("Authorization", jwt);
       httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

        return ResponseEntity.ok(Result.success("success"));
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public ResponseEntity<Result> logout() {
        SecurityUtils.getSubject().logout();

        return ResponseEntity.ok(Result.success(null));
    }
}
