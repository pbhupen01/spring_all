package com.practice.spring.model;


import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void givenObjectsWithSameFields_whenTestedEquals_thenAssertEquals()
    {
        // given
        User first = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        User second = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // then
        Assert.assertEquals(first, second);
    }

    @Test
    public void givenObjectsWithDiffFields_whenTestedEquals_thenAssertNotEquals()
    {
        // given
        User first = new User("TestUser", "Test User", "TestUser", "test@test.com");

        // when
        User second = new User("TestUser1", "Test User", "TestUser", "test@test.com");

        // then
        Assert.assertNotEquals(first, second);
    }
}
