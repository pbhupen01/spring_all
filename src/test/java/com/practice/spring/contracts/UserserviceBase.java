package com.practice.spring.contracts;

import com.practice.spring.Application;
import com.practice.spring.controller.UserController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
//@WebIntegrationTest({"server.port=0", "management.port=0"})
@Ignore
public class UserserviceBase {

    @Autowired
    UserController userController;

    @Before
    public void setUp()
    {
        RestAssuredMockMvc.standaloneSetup(userController);
    }

}
