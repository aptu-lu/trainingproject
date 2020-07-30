package ru.bellintegrator.trainingproject.controller.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class OfficeControllerTest {

        @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void list() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setOrgId(1);
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0]", aMapWithSize(3)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Победа")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1]", aMapWithSize(3)))
                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].name", is("Звезда")))
                .andExpect(jsonPath("$.data[1].isActive", is(false)));
    }

    @Test()
    void exceptionFromList() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setOrgId(4);
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void getOffice() throws Exception {
        mockMvc.perform(get("/api/office/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", aMapWithSize(5)))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.name", is("Звезда")))
                .andExpect(jsonPath("$.data.address", is("ул. Лунина 7а")))
                .andExpect(jsonPath("$.data.phone", nullValue()))
                .andExpect(jsonPath("$.data.isActive",is(false)));
    }

    @Test
    void exceptionFromGetOffice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/office/7"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void update() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setId(3);
        officeFilter.setName("Роса");
        officeFilter.setAddress("ул. Карпушкина 4");
        mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.RESULT", is("success")));
    }

    @Test
    void exceptionFromUpdate() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setId(5);
        officeFilter.setName("Калина");
        officeFilter.setAddress("ул. Карпушкина 4");
        mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void save() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setOrgId(2);
        officeFilter.setName("Пушка");
        mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.RESULT", is("success")));
    }

    @Test
    void exceptionFromSave() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }
}