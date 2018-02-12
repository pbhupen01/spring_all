package com.practice.spring.service;

import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.model.Diary;
import com.practice.spring.model.User;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

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
    public User searchUserByUserId(String userId) throws UserNotFoundException {
        log.debug("Searching user with id: " + userId);
        User userDAO = userRepository.findOne(userId);
        if(userDAO == null)
        {
            log.error("User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        log.info("User found: " + userDAO);
        return userDAO;
    }

    @Override
    public User createUser(@Valid User userDAO) throws UserAlreadyExistsException{
        String userId = userDAO.getUserId();
        log.debug("Searching user with id: " + userId);
        User searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO != null)
        {
            log.error("Cannot create new user. User already exists with Id: " + userId);
            throw new UserAlreadyExistsException(userId);
        }
        User returnedUserDAO = userRepository.save(userDAO);
        log.info("User created " + returnedUserDAO);
        return returnedUserDAO;
    }

    @Override
    public User updateUser(User userDAO) throws UserNotFoundException {
        String userId = userDAO.getUserId();
        log.debug("Searching user with id: " + userId);
        User searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            log.error("Cannot update user. User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        User returnedUserDAO = userRepository.save(userDAO);
        log.info("User updated " + returnedUserDAO);
        return returnedUserDAO;
    }

    @Override
    public void deleteUserByUserId(String userId) throws UserNotFoundException {
        log.debug("Searching user with id: " + userId);
        User searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            log.error("Cannot delete user. User not found with Id: " + userId);
            throw new UserNotFoundException(userId);
        }
        userRepository.delete(userId);
        log.info("User deleted with userId: " + userId);
    }

    @Override
    public Page<User> getAllUsers(Integer page, Integer pageSize) {
        log.debug("Retrieving all users. Page: " + page + ". Pagsize: "+ pageSize);
        Page<User> users = userRepository.findAll( new PageRequest(page, pageSize));
        log.debug("Retrieve successful for all users. Page: " + page + ". Pagsize: "+ pageSize);
        return users;
    }
}
