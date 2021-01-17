package com.kkngai.blogjava;

import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.respository.UserRepository;
import com.kkngai.blogjava.service.UserService;
import com.kkngai.blogjava.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 10/1/2021, 星期日
 **/
@SpringBootTest
@ActiveProfiles(profiles = "test") // load in-memory db instead of normal db
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;


    @Test
    public void getAllUsers() {
        userRepository.save(User.builder().username("Tommy").status(0).build());
        userRepository.save(User.builder().username("John").status(0).build());

        List<User> userList = userService.findAll();

        Assertions.assertEquals(2, userList.size());
    }

    @Test
    public void getUser() {
        User expectedUser = User.builder().id(1).username("Tommy").status(0).build();
        userRepository.save(expectedUser);

        User actualUser = userService.findById(1).orElse(null);

        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expectedUser.getStatus(), actualUser.getStatus());

    }

    @Test
    @Disabled
    public void givenUser_whenFindByUsername_thenReturnUsers() {
        userRepository.save(User.builder().username("Tommy").status(0).build());
        userRepository.save(User.builder().username("John").status(0).build());

        Optional<User> optionalUser = userService.findByUserName("Tommy");

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals("Tommy", optionalUser.get().getUsername());
    }
}
