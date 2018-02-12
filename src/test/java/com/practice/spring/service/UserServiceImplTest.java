package com.practice.spring.service;

import com.practice.spring.model.User;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
 * http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
 *
 * Focued on UserServiceImple code only.
 * So mocking repository
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceImpl.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        User userDAO1 = new User("TestUser", "Test User", "TestUser", "test@test.com");
        User userDAO2 = new User("CreateUser", "Create User", "TestUser", "test@test.com");
        User userDAO3 = new User("TestUser", "Update User", "TestUser", "test@test.com");

        Mockito.when(userRepository.findOne(userDAO1.getUserId()))
                .thenReturn(userDAO1);

        Mockito.when(userRepository.findOne(userDAO2.getUserId()))
                .thenReturn(null);

        Mockito.when(userRepository.save(userDAO2))
                .thenReturn(userDAO2);

        Mockito.when(userRepository.save(userDAO3))
                .thenReturn(userDAO3);
    }

    @Test
    public void givenExistingUserId_whenSearchUser_thenReturnUser() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        User userDAO = userService.searchUserByUserId(searchUserDAO.getUserId());

        //then
        Assert.assertEquals(userDAO, searchUserDAO);

    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUserId_whenSearchUser_thenThrowException() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.searchUserByUserId(searchUserDAO.getUserId());
    }

    @Test
    public void givenNonExistingUser_whenCreateUser_thenCreateUser() throws UserAlreadyExistsException
    {
        // given
        User userDAO2 = new User("CreateUser", "Create User", "TestUser", "test@test.com");

        // when
        User createdUserDAO = userService.createUser(userDAO2);

        // then
        Assert.assertEquals(createdUserDAO, userDAO2);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void givenExistingUser_whenCreateUser_thenThrowException() throws UserAlreadyExistsException
    {
        //given
        User searchUserDAO = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.createUser(searchUserDAO);
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenUpdateUser() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("TestUser", "Update User", "TestUser", "test@test.com");

        // when
        User updatedUserDAO = userService.updateUser(searchUserDAO);

        // then
        Assert.assertEquals(updatedUserDAO, searchUserDAO);
    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUser_whenUpdateUser_thenThrowException() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.updateUser(searchUserDAO);

    }

    @Test
    public void givenExistingUserId_whenDeleteUser_thenDeleteUser() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.deleteUserByUserId(searchUserDAO.getUserId());

    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUserId_whenDeleteUser_thenThrowException() throws UserNotFoundException
    {
        //given
        User searchUserDAO = new User("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.deleteUserByUserId(searchUserDAO.getUserId());

    }
}
