package com.practice.spring.exception;

import java.util.Date;

public class DiaryNotFoundException extends Exception {

    private String userId;
    private Date date;

    public DiaryNotFoundException(String userId, Date date)
    {
        super(String.format("Diary for User with ID '%s' at Date %tD not found", userId, date));
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
