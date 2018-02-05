package com.practice.spring.dao;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryDAO {

    String userId;
    Date date;
    String message;
}
