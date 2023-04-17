package com.example.awsomepastebin.controller;

import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.repository.PastRepos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PastControllerTest {

    @Autowired
    private PastRepos pastRepos;

    @Autowired
    private MockMvc mockMvc;

    private Past past1;
    private Past past2;
    private JSONObject past3;

    @BeforeEach
    void setUp() throws JSONException {
        past1 = new Past();
        past1.setTitle("title1");
        past1.setBody("body1");
        String id1 = pastRepos.save(past1).getId();

        past2 = new Past();
        past2.setTitle("title2");
        past2.setBody("body2");
        past2.setStatus(Status.PUBLIC);

        past3.put("title", "title3");
        past3.put("body", "body3");
        past3.put("status", "PUBLIC");
    }

    @AfterEach
    void tearDown() {
        pastRepos.deleteAll();
    }

    public static String asJsonString(final Past past) {
        try {
            return new ObjectMapper().writeValueAsString(past);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void create() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin.tld/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(past2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(past2.getTitle()));
    }

    @Test
    void getTenLastPublicPasts() {
    }

    @Test
    void getById() {
    }

    @Test
    void search() {
    }
}