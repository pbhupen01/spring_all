package com.practice.spring.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String userId)
    {
        super(String.format("User with ID '%s' already exists", userId));
    }
}
