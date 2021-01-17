package com.kkngai.blogjava.common.exception;

import com.kkngai.blogjava.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author : kkngai
 * @created : 15/1/2021, 星期五
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle shiro exception
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handleUnauthorized(ShiroException e) {
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegal(IllegalArgumentException e) throws IOException {
        log.error("Assert exception: [{}]", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleNotValid(MethodArgumentNotValidException e) {
        log.error("Data invalid exception: [{}]", e.getMessage());
        BindingResult bindResult = e.getBindingResult();
        ObjectError objectError = bindResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("Runtime exception: [{}]", e.getMessage());
        return Result.fail(e.getMessage());
    }
}
