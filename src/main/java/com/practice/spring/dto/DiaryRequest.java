package com.practice.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryRequest {

    Date date;
    String message;
}
