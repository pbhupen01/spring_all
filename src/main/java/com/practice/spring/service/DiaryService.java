package com.practice.spring.service;

import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.model.Diary;

import java.util.Date;

public interface DiaryService {

    public void deleteDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException;

    public Diary searchDiaryForUser(String userId, Date date) throws UserNotFoundException, DiaryNotFoundException;

    public Diary updateDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryNotFoundException;

    public Diary createDiaryForUser(String userId, Diary diary) throws UserNotFoundException, DiaryAlreadyExistsException;
}
