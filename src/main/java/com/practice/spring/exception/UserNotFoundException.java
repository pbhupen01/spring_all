package com.practice.spring.exception;

public class UserNotFoundException extends Exception {

    String userId;

    public UserNotFoundException(String userId)
    {
        super(String.format("User with ID '%s' not found", userId));
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
