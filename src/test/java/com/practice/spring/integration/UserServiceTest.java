package com.practice.spring.integration;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class does integration testing.
 * It may use stubs of producers for this consumer service.
 * It may use actual database or mock
 *
 * This class is also part of contract testing for this consumer service.
 *
 * To me it seems to be duplication of Contract tests generated for this consumer service.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureStubRunner
@Ignore
public class UserServiceTest {
}
