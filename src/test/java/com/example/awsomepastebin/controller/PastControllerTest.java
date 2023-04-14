package com.example.awsomepastebin.controller;

import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.repository.PastRepos;
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
        past1.setTitle("title1");
        past1.setBody("body1");
        pastRepos.save(past1);

        past1.setTitle("title2");
        past1.setBody("body2");
        pastRepos.save(past2);

        past3.put("title", "title3");
        past3.put("body", "body3");
    }

    @AfterEach
    void tearDown() {
        pastRepos.deleteAll();
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin.tld/save")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(past3.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.title").value("title3"));

//        mockMvc.perform(get("//my-awesome-pastebin.tld/id=" + ))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[1].name").value("test_name"));
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