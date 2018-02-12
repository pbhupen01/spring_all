package com.practice.spring.exception;

import java.util.Date;

public class DiaryAlreadyExistsException extends Exception {

    private String userId;
    private Date date;

    public DiaryAlreadyExistsException(String userId, Date date)
    {
        super(String.format("Diary for User with ID '%s' at Date %tD already exists", userId, date));
        this.userId = userId;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
