package ru.bellintegrator.trainingproject.controller.docs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class DocsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllDocs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docs"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(13)))
                .andExpect(jsonPath("$.data[0].code", is("03")))
                .andExpect(jsonPath("$.data[0].name", is("Свидетельство о рождении")))
                .andExpect(jsonPath("$.data[1].code", is("07")))
                .andExpect(jsonPath("$.data[1].name", is("Военный билет")));
    }
}