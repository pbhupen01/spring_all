package com.practice.spring.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
/*@Entity
@Table(name = "diaries")*/
public class DiaryDAO {

    String userId;
    Date date;
    String message;
}
