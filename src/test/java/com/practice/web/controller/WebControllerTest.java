package com.practice.web.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenNoUser_whenGreetings_thenReturnHelloWorld() throws Exception {
        // given
        String url = "/web/greeting";

        // when
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("greeting"))
                //.andExpect(MockMvcResultMatchers.model().attribute("name", ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUser_whenGreetings_thenReturnHelloUser() throws Exception
    {
        // given
        String name = "userName";
        String url = "/web/greeting?name="+name;

        // when
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("greeting"))
                //.andExpect(MockMvcResultMatchers.model().attribute("name", ))
                .andDo(MockMvcResultHandlers.print());

    }

}
