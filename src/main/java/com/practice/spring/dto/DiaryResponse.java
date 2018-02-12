package com.practice.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryResponse {

    String userId;
    Date date;
    String message;
}
