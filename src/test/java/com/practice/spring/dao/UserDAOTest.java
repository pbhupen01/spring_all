package com.practice.spring.dao;


import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {

    @Test
    public void whenGivenObjectWithSamefields_returnTrue()
    {
        // given
        UserDAO first = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        UserDAO second = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // then
        Assert.assertEquals(first, second);
    }

    @Test
    public void whenGivenObjectWithDifferentUserId_returnTrue()
    {
        // given
        UserDAO first = new UserDAO("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        UserDAO second = new UserDAO("TestUser1", "Test User", "TestUser", "test@test.com");

        // then
        Assert.assertNotEquals(first, second);
    }
}
