package com.demoapp.votingpoll.controller;

import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Data
public class VoteControllerTest {

    @Autowired
    private MockMvc mvc;

    private static Integer sessionId;

    private static boolean initialized;

    @Before
    public void setUp() throws Exception {
        if(!initialized) {
            MvcResult result = mvc.perform(post("/v1/subject")
                .content("{\"name\": \"subject3\"}") //TODO - Transform in object instead of using raw String Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

            String subjectName = JsonPath.parse(result.getResponse().getContentAsString()).read("$['name']");

            result = mvc.perform(post("/v1/session")
                .content("{\"duration\": 0,\"subject\": \"" + subjectName + "\"}") //TODO - Transform in object instead of using raw String Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

            sessionId = JsonPath.parse(result.getResponse().getContentAsString()).read("$['id']");
            initialized = true;
        }
    }

    @Test
    public void shouldSuccessfullyVote() throws Exception {
        mvc.perform(post("/v1/vote")
            .content("{\"cpf\": \"19839091069\",\"sessionId\":" + sessionId + ",\"voteType\": \"Sim\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveErrorWhenCpfIsInvalid() throws Exception {
        mvc.perform(post("/v1/vote")
            .content("{\"cpf\": \"12345678910\",\"sessionId\":" + sessionId + ",\"voteType\": \"Sim\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("{\"error\":\"Cpf could not be validated\"}"));
    }

    @Test
    public void shouldReceiveErrorWhenTypeIsInvalid() throws Exception {
        mvc.perform(post("/v1/vote")
            .content("{\"cpf\": \"19839091069\",\"sessionId\":" + sessionId + ",\"voteType\": \"S\"}") //TODO - Transform in object instead of using raw String Json
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("{\"error\":\"Invalid vote type\"}"));
    }
}
