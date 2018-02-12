package com.practice.spring.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserResponse {

    @NotNull
    String userId;
    @NotNull
    String name;
    @NotNull
    String emailId;
}
