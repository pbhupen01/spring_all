package com.practice.spring;

import cucumber.api.junit.*;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@Cucumber.Options(
        features={"src/test/resources/features"},
        glue ={"stepdefs"}
)

public class CukesRunner {
}
