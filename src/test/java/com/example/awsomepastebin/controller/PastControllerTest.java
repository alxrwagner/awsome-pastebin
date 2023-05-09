package com.example.awsomepastebin.controller;

import com.example.awsomepastebin.enums.ExpiryDate;
import com.example.awsomepastebin.enums.Status;
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

    @BeforeEach
    void setUp(){
        past1 = new Past();
        past1.setTitle("title1");
        past1.setBody("body1");
        past1.setStatus(Status.PUBLIC);
        past1.setId("1");
        past1.setId(pastRepos.save(past1).getId());
    }

    @AfterEach
    void tearDown() {
        pastRepos.deleteAll();
    }

    public static String asJsonString() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "title3");
        jsonObject.put("body", "title3");
        jsonObject.put("status", Status.PUBLIC);
        jsonObject.put("expiryDate", ExpiryDate.TEN_MINUTES);
        return jsonObject.toString();
    }
    @Test
    void create() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin.tld/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").isString());
    }

    @Test
    void getTenLastPublicPasts() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/last10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/" + past1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title1"));
    }

    @Test
    void getById_ifIdNotFound() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/123qwe"))
                .andExpect(status().isNotFound());
    }

    @Test
    void search() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/search?title=" + past1.getTitle()))
                .andExpect(status().isOk());
    }
}