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

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Data
public class ResultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetResultsSuccessfully() throws Exception {

        MvcResult result = mvc.perform(post("/v1/subject")
            .content("{\"name\": \"subject4\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

        String subjectName = JsonPath.parse(result.getResponse().getContentAsString()).read("$['name']");

        result = mvc.perform(post("/v1/session")
            .content("{\"duration\": 0,\"subject\": \"" + subjectName + "\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

        Integer sessionId = JsonPath.parse(result.getResponse().getContentAsString()).read("$['id']");

        mvc.perform(post("/v1/vote")
            .content("{\"cpf\": \"19839091069\",\"sessionId\":" + sessionId + ",\"voteType\": \"Sim\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        mvc.perform(get("/v1/results")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }
}
