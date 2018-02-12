package com.practice.spring.service;

import com.practice.spring.model.User;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import org.springframework.data.domain.Page;


public interface UserService {

    public User searchUserByUserId(String userId) throws UserNotFoundException;

    public User createUser(User userDAO) throws UserAlreadyExistsException;

    public User updateUser(User userDAO) throws UserNotFoundException;

    public void deleteUserByUserId(String userId) throws UserNotFoundException;

    public Page<User> getAllUsers(Integer page, Integer pageSize);
}
