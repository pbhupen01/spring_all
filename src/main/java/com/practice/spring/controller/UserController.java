package com.practice.spring.controller;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.dto.PageableCollection;
import com.practice.spring.dto.UserRequest;
import com.practice.spring.dto.UserResponse;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.service.UserService;
import com.practice.spring.util.SpringAllUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = SpringAllUtils.USERS)
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping( value = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) throws UserNotFoundException
    {
        UserDAO userDAO = userService.searchUserByUserId(userId);
        UserResponse response = convertUserDAOToUserResponse(userDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException
    {
        UserDAO userDAO = convertUserRequestRequestToUserDAO(userRequest);
        UserDAO createdUserDAO = userService.createUser(userDAO);
        UserResponse response = convertUserDAOToUserResponse(createdUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) throws UserNotFoundException {
        UserDAO userDAO = convertUserRequestRequestToUserDAO(userRequest);
        UserDAO updatedUserDAO = userService.updateUser(userDAO);
        UserResponse response = convertUserDAOToUserResponse(updatedUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @DeleteMapping( value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) throws UserNotFoundException
    {
        userService.deleteUserByUserId(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageableCollection<UserResponse>> getUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        Page<UserDAO> userDAOs = userService.getAllUsers(page, pageSize);
        PageableCollection<UserResponse> response = new PageableCollection(
                userDAOs.getTotalElements(),
                userDAOs.getTotalPages(),
                userDAOs.getContent().stream().map(this::convertUserDAOToUserResponse).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private UserResponse convertUserDAOToUserResponse(UserDAO userDAO)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userDAO.getDisplayName());
        userResponse.setEmailId(userDAO.getEmailId());
        return userResponse;
    }

    private UserDAO convertUserRequestRequestToUserDAO(UserRequest userRequest)
    {
        UserDAO userDAO = new UserDAO();
        userDAO.setDisplayName(userRequest.getName());
        userDAO.setEmailId(userRequest.getEmailId());
        userDAO.setPassword(userRequest.getPassword());
        userDAO.setUserId(userRequest.getUserId());
        return userDAO;
    }
}
