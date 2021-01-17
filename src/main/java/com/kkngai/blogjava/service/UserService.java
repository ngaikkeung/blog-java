package com.kkngai.blogjava.service;

import com.kkngai.blogjava.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 9/1/2021, 星期六
 **/
public interface UserService {

    Optional<User> findById(long id);

    List<User> findAll();

    Optional<User> findByUserName(String username);
}
