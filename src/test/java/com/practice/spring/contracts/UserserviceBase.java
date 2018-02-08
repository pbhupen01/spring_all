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

/**
 * Base class for this producer service API testing.
 * Spring cloud contract generates tests from contracts. These test classes will extend this base class.
 * This class is used to setup the producer service.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
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
