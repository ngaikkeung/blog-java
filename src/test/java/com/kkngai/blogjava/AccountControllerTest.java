package com.kkngai.blogjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkngai.blogjava.controller.AccountController;
import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.service.UserService;
import com.kkngai.blogjava.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@WebMvcTest(controllers = AccountController.class )
@Disabled
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtils jwtUtils;

    @MockBean
    UserServiceImpl userService;

    @Test
    public void givenRegister_whenUserLogin_thenReturn200AndJwt() throws Exception{
        User expectedUser = User.builder()
                                .id(1)
                                .username("John")
                                .password("1234")
                                .email("john1234@gmail.com")
                                .build();

        Mockito.when(userService.findByUserName(expectedUser.getUsername())).thenReturn(Optional.of(expectedUser));

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                                            .post("/account/login")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .header("Origin", "*")
                                            .content(objectMapper.writeValueAsString(expectedUser)))
                                    .andExpect(status().isOk())
                                    .andReturn();
    }
}
