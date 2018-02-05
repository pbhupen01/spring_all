package com.practice.spring.service;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.dto.User;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User searchUserByUserId(String userId) throws UserNotFoundException {
        UserDAO userDAO = userRepository.findOne(userId);
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
