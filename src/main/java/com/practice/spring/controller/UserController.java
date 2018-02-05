package com.practice.spring.controller;

import com.practice.spring.dto.User;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.service.UserService;
import com.practice.spring.util.SpringAllUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(SpringAllUtils.USERS)
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(value = "/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String emailId) throws UserNotFoundException
    {
        return userService.searchUserByEmailId(emailId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUsers()
    {
        User a = new User();
        a.setName("namea");
        User b = new User();
        b.setName("nameb");
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        return list;
    }
}
