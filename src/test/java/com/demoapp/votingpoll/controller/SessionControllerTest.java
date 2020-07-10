package com.demoapp.votingpoll.controller;

import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Data
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnHttpStatusSuccess() throws Exception {

        MvcResult result = mvc.perform(post("/v1/subject")
            .content("{\"name\": \"subject2\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

        String name = JsonPath.parse(result.getResponse().getContentAsString()).read("$['name']");

        mvc.perform(post("/v1/session")
            .content("{\"duration\": 0,\"subject\": \"" + name + "\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
