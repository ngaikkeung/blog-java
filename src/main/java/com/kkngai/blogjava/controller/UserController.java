package com.kkngai.blogjava.controller;

import com.kkngai.blogjava.common.lang.Result;
import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author : kkngai
 * @created : 9/1/2021, 星期六
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    ResponseEntity<Result> getAllUsers() {
        return ResponseEntity.ok(Result.success(userService.findAll()));
    }

    @GetMapping("/{id}")
    ResponseEntity<Result> getUser(@PathVariable("id") long id) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.ok(Result.fail("0 result"));
        }
        return ResponseEntity.ok(Result.success(optionalUser.get()));
    }

    @GetMapping("/unauthenticated")
    ResponseEntity<Result> unAuthenticatedTest() throws Exception {
        throw new UnauthenticatedException();
    }

    @PostMapping("/create")
    public ResponseEntity<Result> createUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(Result.success("success", user));
    }
}
