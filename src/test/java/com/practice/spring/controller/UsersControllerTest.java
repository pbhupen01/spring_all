package com.practice.spring.controller;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Focused on UserController Code only
 * So Mocking UserService
 *
 * https://memorynotfound.com/unit-test-spring-mvc-rest-service-junit-mockito/
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenExistingUserId_whenGetUser_thenReturnUser() throws Exception
    {
        UserDAO userDAO1 = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // given
        Mockito.when(userService.searchUserByUserId(userDAO1.getUserId())).thenReturn(userDAO1);

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userDAO1.getUserId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
                //.andExpect(MockMvcResultMatchers.content().json());
    }

    @Test
    public void givenNonExistingUserId_whenGetUser_thenReturnNotFound() throws Exception
    {
        String userId = "test";

        // given
        Mockito.when(userService.searchUserByUserId(userId)).thenThrow(new UserNotFoundException(userId));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenNonExistingUser_whenCreateUser_thenCreateUser() throws Exception
    {
        UserDAO userDAO1 = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // given
        Mockito.when(userService.createUser(userDAO1)).thenReturn(userDAO1);

        // when then
        /*mockMvc.perform(MockMvcRequestBuilders.post("/users/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());*/
        //.andExpect(MockMvcResultMatchers.content().json());
    }

}
