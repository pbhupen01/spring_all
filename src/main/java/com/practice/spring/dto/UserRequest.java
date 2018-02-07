package com.practice.spring.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull
    String userId;
    @NotNull
    String name;
    @NotNull
    String password;
    @NotNull
    String emailId;

    public String toString()
    {
        return String.format("UserId: %s, Name: %s, EmailId: %s", userId, name, emailId);
    }

}
