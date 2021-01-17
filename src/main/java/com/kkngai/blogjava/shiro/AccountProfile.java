package com.kkngai.blogjava.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : kkngai
 * @created : 14/1/2021, 星期四
 **/
@Data
public class AccountProfile implements Serializable {
    private long id;
    private String username;
    private String avatar;
}
