package com.practice.spring.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
//@Entity
//@Table(name = "diaries")
public class Diary {

    //@EmbeddedId
    DiaryID key;
    String message;
}
