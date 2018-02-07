package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDAO searchUserByUserId(String userId) throws UserNotFoundException {
        log.debug("Searching user with id: " + userId);
        UserDAO userDAO = userRepository.findOne(userId);
        if(userDAO == null)
        {
            log.error("User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        log.info("User found: " + userDAO);
        return userDAO;
    }

    @Override
    public UserDAO createUser(@Valid UserDAO userDAO) throws UserAlreadyExistsException{
        String userId = userDAO.getUserId();
        log.debug("Searching user with id: " + userId);
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO != null)
        {
            log.error("Cannot create new user. User already exists with Id: " + userId);
            throw new UserAlreadyExistsException(userId);
        }
        UserDAO returnedUserDAO = userRepository.save(userDAO);
        log.info("User created " + returnedUserDAO);
        return returnedUserDAO;
    }

    @Override
    public UserDAO updateUser(UserDAO userDAO) throws UserNotFoundException {
        String userId = userDAO.getUserId();
        log.debug("Searching user with id: " + userId);
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            log.error("Cannot update user. User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        UserDAO returnedUserDAO = userRepository.save(userDAO);
        log.info("User updated " + returnedUserDAO);
        return returnedUserDAO;
    }

    @Override
    public void deleteUserByUserId(String userId) throws UserNotFoundException {
        log.debug("Searching user with id: " + userId);
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            log.error("Cannot delete user. User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        userRepository.delete(userId);
        log.info("User deleted with userId: " + userId);
    }

    @Override
    public Page<UserDAO> getAllUsers(Integer page, Integer pageSize) {
        log.debug("Retrieving all users. Page: " + page + ". Pagsize: "+ pageSize);
        Page<UserDAO> users = userRepository.findAll( new PageRequest(page, pageSize));
        log.debug("Retrieve successful for all users. Page: " + page + ". Pagsize: "+ pageSize);
        return users;
    }
}
