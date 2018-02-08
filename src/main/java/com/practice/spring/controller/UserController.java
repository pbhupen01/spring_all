package com.practice.spring.controller;

import com.practice.spring.dao.UserDAO;
import com.practice.spring.dto.PageableCollection;
import com.practice.spring.dto.UserRequest;
import com.practice.spring.dto.UserResponse;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.service.UserService;
import com.practice.spring.util.SpringAllUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = SpringAllUtils.USERS)
//@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @ApiOperation(
            value = "Get user",
            notes = "Returns Json Data with the User details"
    )
    @GetMapping( value = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) throws UserNotFoundException
    {
        log.debug("Got search request for user : " + userId);
        UserDAO userDAO = userService.searchUserByUserId(userId);
        log.debug("Returning found user : " + userId);
        UserResponse response = convertUserDAOToUserResponse(userDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @ApiOperation(
            value = "Create user",
            notes = "Returns Json Data with the created User details"
    )
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException
    {
        log.debug("Got create request for user : " + userRequest);
        UserDAO userDAO = convertUserRequestRequestToUserDAO(userRequest);
        UserDAO createdUserDAO = userService.createUser(userDAO);
        log.info("Created user : " + userRequest);
        UserResponse response = convertUserDAOToUserResponse(createdUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update user",
            notes = "Returns Json Data with the updated User details"
    )
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) throws UserNotFoundException {
        log.debug("Got update request for user : " + userRequest);
        UserDAO userDAO = convertUserRequestRequestToUserDAO(userRequest);
        UserDAO updatedUserDAO = userService.updateUser(userDAO);
        log.info("Updated user : " + userRequest);
        UserResponse response = convertUserDAOToUserResponse(updatedUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @ApiOperation(
            value = "Delete user",
            notes = "Deletes user by userId"
    )
    @DeleteMapping( value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) throws UserNotFoundException
    {
        log.debug("Got delete request for user : " + userId);
        userService.deleteUserByUserId(userId);
        log.info("Deleted user : " + userId);
    }

    @ApiOperation(
            value = "Get all users",
            notes = "Returns Json Data with all the Users details"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageableCollection<UserResponse>> getUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        log.debug(String.format("Got getUsers request. Page %d. Pagesize %d", page, pageSize));
        Page<UserDAO> userDAOs = userService.getAllUsers(page, pageSize);
        log.debug(String.format("Found users. Total: %d Pages: %d", userDAOs.getTotalElements(), userDAOs.getTotalPages()));
        PageableCollection<UserResponse> response = new PageableCollection(
                userDAOs.getTotalElements(),
                userDAOs.getTotalPages(),
                userDAOs.getContent().stream().map(this::convertUserDAOToUserResponse).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@GetMapping(value = "/{userId}" + SpringAllUtils.DIARIES)
    public ResponseEntity<Diary> getDiaryForUserForDate(@PathVariable String userId,
                                                        @RequestParam(value="date", required = true) @DateTimeFormat(pattern="dd-MM-yyyy") Date date)
    {
        log.debug(String.format("Got getDiaryForUserForDate request. UserId %s. Date %s", userId, date));
        Diary response = new Diary();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    private UserResponse convertUserDAOToUserResponse(UserDAO userDAO)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userDAO.getDisplayName());
        userResponse.setEmailId(userDAO.getEmailId());
        userResponse.setUserId(userDAO.getUserId());
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
