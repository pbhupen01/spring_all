package com.practice.spring.service;

import com.practice.spring.dto.User;
import com.practice.spring.exception.UserNotFoundException;

public interface UserService {

    public User searchUserByEmailId(String emailId) throws UserNotFoundException;

    public void getAllUsers();
}
