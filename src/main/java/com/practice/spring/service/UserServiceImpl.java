package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.dto.User;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User searchUserByEmailId(String emailId) throws UserNotFoundException {
        UserDAO userDAO = userRepository.findOne(emailId);
        if(userDAO == null)
        {
            throw new UserNotFoundException(String.format("User %s not found"));
        }
        return convertUserDAOToUser(userDAO);
    }

    @Override
    public void getAllUsers() {
        userRepository.findAll();
    }

    private User convertUserDAOToUser(UserDAO userDAO)
    {
        User user = new User();
        user.setName(userDAO.getDisplayName());
        user.setEmailId(userDAO.getEmailId());
        user.setPassword(userDAO.getPassword());
        return user;
    }
}
