package com.practice.spring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
/*@Entity
@Table(name = "diaries")*/
public class Diary {

    String userId;
    Date date;
    String message;
}
