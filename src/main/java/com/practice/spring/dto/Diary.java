package com.practice.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Diary {

    String userId;
    Date date;
    String message;
}
