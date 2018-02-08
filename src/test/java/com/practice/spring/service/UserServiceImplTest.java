package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
        UserDAO userDAO1 = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");
        UserDAO userDAO2 = new UserDAO("CreateUser", "Create User", "TestUser", "test@test.com");
        UserDAO userDAO3 = new UserDAO("TestUser", "Update User", "TestUser", "test@test.com");

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
        UserDAO searchUserDAO = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        UserDAO userDAO = userService.searchUserByUserId(searchUserDAO.getUserId());

        //then
        Assert.assertEquals(userDAO, searchUserDAO);

    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUserId_whenSearchUser_thenThrowException() throws UserNotFoundException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.searchUserByUserId(searchUserDAO.getUserId());
    }

    @Test
    public void givenNonExistingUser_whenCreateUser_thenCreateUser() throws UserAlreadyExistsException
    {
        // given
        UserDAO userDAO2 = new UserDAO("CreateUser", "Create User", "TestUser", "test@test.com");

        // when
        UserDAO createdUserDAO = userService.createUser(userDAO2);

        // then
        Assert.assertEquals(createdUserDAO, userDAO2);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void givenExistingUser_whenCreateUser_thenThrowException() throws UserAlreadyExistsException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.createUser(searchUserDAO);
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenUpdateUser() throws UserNotFoundException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("TestUser", "Update User", "TestUser", "test@test.com");

        // when
        UserDAO updatedUserDAO = userService.updateUser(searchUserDAO);

        // then
        Assert.assertEquals(updatedUserDAO, searchUserDAO);
    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUser_whenUpdateUser_thenThrowException() throws UserNotFoundException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.updateUser(searchUserDAO);

    }

    @Test
    public void givenExistingUserId_whenDeleteUser_thenDeleteUser() throws UserNotFoundException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.deleteUserByUserId(searchUserDAO.getUserId());

    }

    @Test(expected = UserNotFoundException.class)
    public void givenNonExistingUserId_whenDeleteUser_thenThrowException() throws UserNotFoundException
    {
        //given
        UserDAO searchUserDAO = new UserDAO("newUser", "Test User", "TestUser", "test@test.com");

        // when
        userService.deleteUserByUserId(searchUserDAO.getUserId());

    }
}
