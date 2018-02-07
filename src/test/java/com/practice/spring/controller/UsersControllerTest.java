package com.practice.spring.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.practice.spring.contracts.UserserviceBase;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

@Slf4j
@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersControllerTest extends UserserviceBase {

    @Test
    public void validate_1_shouldCreateUser() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body("{\"userId\":\"test-user\",\"name\":\"Test User\",\"password\":\"test-user\",\"emailId\":\"testUser@test.com\"}");

        // when:
        ResponseOptions response = given().spec(request)
                .post("/users");

        // then:
        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.header("Content-Type")).matches("application/json.*");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['name']").isEqualTo("Test User");
        assertThatJson(parsedJson).field("['emailId']").isEqualTo("testUser@test.com");
        assertThatJson(parsedJson).field("['userId']").isEqualTo("test-user");
    }

    @Test
    public void validate_2_shouldReturnAllUsers() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json");

        // when:
        ResponseOptions response = given().spec(request)
                .get("/users");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).matches("application/json.*");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).array("['content']").contains("['name']").isEqualTo("Test User");
        assertThatJson(parsedJson).field("['totalPages']").isEqualTo(1);
        assertThatJson(parsedJson).array("['content']").contains("['emailId']").isEqualTo("testUser@test.com");
        assertThatJson(parsedJson).field("['totalElements']").isEqualTo(1);
        assertThatJson(parsedJson).array("['content']").contains("['userId']").isEqualTo("test-user");
    }

    @Test
    public void validate_3_shouldReturnUser() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json");

        // when:
        ResponseOptions response = given().spec(request)
                .get("/users/test-user");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).matches("application/json.*");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['name']").isEqualTo("Test User");
        assertThatJson(parsedJson).field("['emailId']").isEqualTo("testUser@test.com");
        assertThatJson(parsedJson).field("['userId']").isEqualTo("test-user");
    }

    @Test
    public void validate_4_shouldUpdateUser() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body("{\"userId\":\"test-user\",\"name\":\"Utest User\",\"password\":\"test-user\",\"emailId\":\"utest@test.com\"}");

        // when:
        ResponseOptions response = given().spec(request)
                .put("/users");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).matches("application/json.*");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['emailId']").isEqualTo("utest@test.com");
        assertThatJson(parsedJson).field("['name']").isEqualTo("Utest User");
        assertThatJson(parsedJson).field("['userId']").isEqualTo("test-user");
    }

    @Test
    public void validate_5_shouldDeleteUser() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json");

        // when:
        ResponseOptions response = given().spec(request)
                .delete("/users/test-user");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        //assertThat(response.header("Content-Type")).matches("application/json.*");
    }

}
