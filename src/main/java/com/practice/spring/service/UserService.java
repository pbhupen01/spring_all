package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import org.springframework.data.domain.Page;


public interface UserService {

    public UserDAO searchUserByUserId(String userId) throws UserNotFoundException;

    public UserDAO createUser(UserDAO userDAO) throws UserAlreadyExistsException;

    public UserDAO updateUser(UserDAO userDAO) throws UserNotFoundException;

    public void deleteUserByUserId(String userId) throws UserNotFoundException;

    public Page<UserDAO> getAllUsers(Integer page, Integer pageSize);
}
