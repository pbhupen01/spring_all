package com.practice.spring.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
