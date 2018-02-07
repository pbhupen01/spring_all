package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDAO searchUserByUserId(String userId) throws UserNotFoundException {
        UserDAO userDAO = userRepository.findOne(userId);
        if(userDAO == null)
        {
            throw new UserNotFoundException(userId);
        }
        return userDAO;
    }

    @Override
    public UserDAO createUser(@Valid UserDAO userDAO) throws UserAlreadyExistsException{
        String userId = userDAO.getUserId();
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO != null)
        {
            throw new UserAlreadyExistsException(userId);
        }
        UserDAO returnedUserDAO = userRepository.save(userDAO);
        return returnedUserDAO;
    }

    @Override
    public UserDAO updateUser(UserDAO userDAO) throws UserNotFoundException {
        String userId = userDAO.getUserId();
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            throw new UserNotFoundException(userId);
        }
        UserDAO returnedUserDAO = userRepository.save(userDAO);
        return returnedUserDAO;
    }

    @Override
    public void deleteUserByUserId(String userId) throws UserNotFoundException {
        UserDAO searchedUserDAO = userRepository.findOne(userId);
        if(searchedUserDAO == null)
        {
            throw new UserNotFoundException(userId);
        }
        userRepository.delete(userId);
    }

    @Override
    public Page<UserDAO> getAllUsers(Integer page, Integer pageSize) {
        Page<UserDAO> users = userRepository.findAll( new PageRequest(page, pageSize));
        return users;
    }
}
