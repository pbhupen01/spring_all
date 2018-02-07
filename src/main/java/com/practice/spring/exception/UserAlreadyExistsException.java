package com.practice.spring.exception;

public class UserAlreadyExistsException extends Exception {

    private String userId;

    public UserAlreadyExistsException(String userId)
    {
        super(String.format("User with ID '%s' already exists", userId));
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
