package com.practice.spring.contracts;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.practice.spring.Application;
import com.practice.spring.controller.UserController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserserviceBase {

    @Autowired
    UserController userController;

    @Before
    public void setUp()
    {
        RestAssuredMockMvc.standaloneSetup(userController);
    }
}
