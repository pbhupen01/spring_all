package com.practice.spring.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String userId)
    {
        super(String.format("User with ID '%s' not found", userId));
    }
}
