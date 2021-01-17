package com.kkngai.blogjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkngai.blogjava.controller.UserController;
import com.kkngai.blogjava.model.User;
import com.kkngai.blogjava.service.UserService;
import com.kkngai.blogjava.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Disabled
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUsers_whenGetUsers_thenReturnUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder().build());
        users.add(User.builder().build());

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .header("Origin", "*")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is("success")))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void givenOneUser_whenGetUser_thenReturnOneUser() throws Exception {
        User expectedUser = User.builder().id(1).username("John").build();

        Mockito.when(userService.findById(1)).thenReturn(Optional.of(expectedUser));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1")
                        .header("Origin", "*")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is("success")))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.username", is("John")));

    }

    @Test
    public void whenGetAuthorization_then401() throws Exception {
        String authenticateExceptionEndpoint = "/user/unauthenticated";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(authenticateExceptionEndpoint)
                        .header("Origin", "*")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.code", is("-1")))
                .andExpect(jsonPath("$.msg", is(nullValue())))
                .andExpect(jsonPath("$.data", is(nullValue())))
                .andDo(print());
    }

    @Test
    public void givenNewUser_whenCreateUser_thenReturn200AndNewUser() throws Exception {
        User expectedUser = User.builder()
                        .id(1)
                        .username("John")
                        .email("john2020@gmail.com")
                        .status(0)
                        .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                    .post("/user/create")
                                    .header("Origin", "*")
                                    .content(objectMapper.writeValueAsString(expectedUser))
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andDo(print())
                            .andReturn();

        User actualUser = objectMapper.readValue(result.getRequest().getContentAsString(), User.class);

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getStatus(), actualUser.getStatus());

    }

}
