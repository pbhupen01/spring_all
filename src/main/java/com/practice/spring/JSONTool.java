package com.practice.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.spring.dto.user.UserRequest;

import java.io.IOException;

public class JSONTool {

    public static void main(String[] a){
        UserRequest object = new UserRequest();
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            String jsonStr = mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
