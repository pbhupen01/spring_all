package com.practice.spring.service;

import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiaryServiceImpl implements DiaryService{

    UserService userService;

    @Autowired
    public DiaryServiceImpl(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void deleteDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException {
        userService.searchUserByUserId(userId);
        //TODO add code to search diary
        Diary diary = null;
        if(diary == null)
        {
            throw new DiaryNotFoundException(userId, date);
        }
        //TODO add code to delete diary
    }

    @Override
    public Diary searchDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException {
        userService.searchUserByUserId(userId);
        // TODO add code to search diary for user
        Diary diary = null;
        if(diary == null)
        {
            throw new DiaryNotFoundException(userId, date);
        }
        return diary;
    }

    @Override
    public Diary updateDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryNotFoundException {
        userService.searchUserByUserId(userId);
        // TODO add code to search diary for user
        Diary searchedDiary = null;
        if(searchedDiary == null)
        {
            throw new DiaryNotFoundException(userId, diary.getKey().getDate());
        }
        // TODO add code to update diary
        return null;
    }

    @Override
    public Diary createDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryAlreadyExistsException {
        userService.searchUserByUserId(userId);
        // TODO add code to search diary for user
        Diary searchedDiary = null;
        if(searchedDiary == null)
        {
            throw new DiaryAlreadyExistsException(userId, diary.getKey().getDate());
        }
        // TODO add code to create diary
        return null;
    }
}
