package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceImplTest {

    //    @Value("${mano.microservices.runtime-catalog.url}")
    // https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
    // https://gist.github.com/takezoe/d3a14e502801a3e74f989232ae62130c
    // https://dzone.com/articles/mockbean-spring-boots-missing-ingredient
    // http://www.baeldung.com/spring-boot-testing
    // http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        UserDAO userDAO1 = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        Mockito.when(userRepository.findOne(userDAO1.getUserId()))
                .thenReturn(userDAO1);
    }

    @Test
    public void whenValidId_returnUser()
    {
        //given
        UserDAO searchUserDAO = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        UserDAO userDAO = null;
        try {
            userDAO = userService.searchUserByUserId(searchUserDAO.getUserId());
        } catch (UserNotFoundException e) {
        }

        //then
        Assert.assertEquals(userDAO, searchUserDAO);

    }
}
