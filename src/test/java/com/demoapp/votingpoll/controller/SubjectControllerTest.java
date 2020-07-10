package com.demoapp.votingpoll.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Data
public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreateSubjectSuccessfully() throws Exception {
        mvc.perform(post("/v1/subject")
            .content("{\"name\": \"subject1\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveErrorWhenSubjectNameIsEmpty() throws Exception {
        mvc.perform(post("/v1/subject")
            .content("{\"name\": \"\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("{\"name\":\"Name should not be blank.\"}"));
    }
}
