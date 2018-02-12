package com.practice.spring.service;

import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.model.Diary;
import com.practice.spring.model.User;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Date;


public interface UserService {

    public User searchUserByUserId(String userId) throws UserNotFoundException;

    public User createUser(User userDAO) throws UserAlreadyExistsException;

    public User updateUser(User userDAO) throws UserNotFoundException;

    public void deleteUserByUserId(String userId) throws UserNotFoundException;

    public Page<User> getAllUsers(Integer page, Integer pageSize);

    public void deleteDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException;

    public Diary searchDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException;

    public Diary updateDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryNotFoundException;

    public Diary createDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryAlreadyExistsException;
}
