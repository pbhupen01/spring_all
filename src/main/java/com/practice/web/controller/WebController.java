package com.practice.web.controller;

import com.practice.spring.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class WebController {

    @Value("${practice.server.url}")
    private String restServerUrl;

    @RequestMapping("/web/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        log.debug("Got request for greetings with name " + name);
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/web/users")
    public String getUsers(@RequestParam(value="id", required=false) String id, Model model) {
        log.debug("Got request for /web/users with name " + id);
        if(id == null)
        {
            log.info("No id provided in /web/users request");
            model.addAttribute("message", "User Id not provided.");
            return "error";
        }
        try
        {
            log.debug("Got request for /web/users with name " + id);
            RestTemplate restTemplate = new RestTemplate();
            UserResponse userResponse = restTemplate.getForObject(restServerUrl + "users/" + id, UserResponse.class);
            model.addAttribute("user", userResponse);
            return "user";
        }
        catch(Exception e)
        {
            log.error("Error occurred while fetching user " + id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

}
