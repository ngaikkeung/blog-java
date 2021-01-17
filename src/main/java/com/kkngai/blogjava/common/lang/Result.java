package com.kkngai.blogjava.common.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author : kkngai
 * @created : 10/1/2021, 星期日
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return Result.builder()
                .code("0")
                .msg("success")
                .data(data)
                .build();
    }

    public static Result success(String msg, Object data) {
        return Result.builder()
                .code("0")
                .msg(msg)
                .data(data)
                .build();
    }

    public static Result fail(String msg) {
        return Result.builder()
                .code("-1")
                .msg(msg)
                .data(null)
                .build();
    }

    public static Result fail(String msg, Object data) {
        return Result.builder()
                .code("-1")
                .msg(msg)
                .data(data)
                .build();
    }
}
