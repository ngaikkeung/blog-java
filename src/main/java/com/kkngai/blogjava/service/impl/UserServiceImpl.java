package com.kkngai.blogjava.service.impl;

import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.respository.UserRepository;
import com.kkngai.blogjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
