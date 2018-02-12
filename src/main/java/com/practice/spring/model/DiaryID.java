package com.practice.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;

//@Embeddable
@Data
@AllArgsConstructor
public class DiaryID {

    String userId;
    Date date;
}
