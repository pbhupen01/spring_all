package com.practice.spring.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class UserDAO {

    @Id
    String userId;
    String displayName;
    String password;
    String emailId;
}