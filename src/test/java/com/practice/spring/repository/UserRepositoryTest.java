package com.practice.spring.repository;

import com.practice.spring.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Test for Jpa Repository
 *
 * @RunWith(SpringRunner.class)
 * Is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever we are using any Spring Boot testing features in out JUnit tests, this annotation will be required.
 *
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 * Configuring H2, an in-memory database
 * Setting Hibernate, Spring Data, and the DataSource
 * Performing an @EntityScan
 * Turning on SQL logging
 *
 * @TestEntityManager
 * TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp()
    {
        entityManager.persist(new User("TestUser", "Test User", "TestUser", "test@test.com"));
        entityManager.flush();
    }

    @After
    public void tearDown()
    {
        entityManager.clear();
    }



    @Test
    public void givenNonExistingUser_whenCreateUser_thenCreateUser()
    {
        // given
        User userDAO = new User("TestUser2", "Test User2", "TestUser2", "test2@test.com");

        // when
        User createdUserDao = userRepository.save(userDAO);

        // then
        Assert.assertEquals(userDAO, createdUserDao);
    }

    @Test
    public void givenExistingUserId_whenGetUser_thenReturnUser()
    {
        // given
        User userDAO = null;

        // when
        userDAO = userRepository.findOne("TestUser");

        // then
        Assert.assertNotNull(userDAO);

    }

    @Test
    public void givenNoFilter_whenFindAllUsers_thenReturnAllUsers()
    {
        // given
        List<User> listUserDAO = null;

        // when
        listUserDAO = userRepository.findAll();

        // then
        Assert.assertEquals(1, listUserDAO.size());

    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenUpdateUser()
    {
        // given
        User userDAO = new User("TestUser2", "Updated Test User2", "TestUser2", "test2@test.com");

        // when
        User updatedUserDAO = userRepository.save(userDAO);

        // then
        Assert.assertEquals(userDAO, updatedUserDAO);
    }

}
