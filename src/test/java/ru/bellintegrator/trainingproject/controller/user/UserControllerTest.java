package ru.bellintegrator.trainingproject.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.trainingproject.filter.UserFilter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void list() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setOfficeId(2);
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0]", aMapWithSize(5)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].firstName", is("Андрей")))
                .andExpect(jsonPath("$.data[0].lastName", is("Капустин")))
                .andExpect(jsonPath("$.data[0].middleName", is("Олегович")))
                .andExpect(jsonPath("$.data[0].position", is("механик")))
                .andExpect(jsonPath("$.data[1]", aMapWithSize(5)))
                .andExpect(jsonPath("$.data[1].id", is(3)))
                .andExpect(jsonPath("$.data[1].firstName", is("Владимир")))
                .andExpect(jsonPath("$.data[1].lastName", is(nullValue())))
                .andExpect(jsonPath("$.data[1].middleName", is("Владимирович")))
                .andExpect(jsonPath("$.data[1].position", is("строитель")));
    }

    @Test
    void exceptionFromList() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setOfficeId(7);
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", aMapWithSize(12)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("Илья")))
                .andExpect(jsonPath("$.data.lastName", is("Климов")))
                .andExpect(jsonPath("$.data.middleName", is("Андреевич")))
                .andExpect(jsonPath("$.data.phone", is("84394959340")))
                .andExpect(jsonPath("$.data.position", is("слесарь")))
                .andExpect(jsonPath("$.data.docName", is("Удостоверение беженца")))
                .andExpect(jsonPath("$.data.docNumber", is("49385859384")))
                .andExpect(jsonPath("$.data.docDate", is("2000-05-04")))
                .andExpect(jsonPath("$.data.citizenshipName", is("РОССИЯ")))
                .andExpect(jsonPath("$.data.citizenshipCode", is("643")))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    void exceptionFromGetUser() throws Exception {
        mockMvc.perform(get("/api/user/12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void update() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setId(4);
        userFilter.setFirstName("Дмитрий");
        userFilter.setPosition("директор");
        mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.data.RESULT", is("success")));
    }

    @Test
    void exceptionFromUpdate() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setId(15);
        userFilter.setFirstName("Дмитрий");
        userFilter.setPosition("директор");
        mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void save() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setOfficeId(2);
        userFilter.setFirstName("Олег");
        userFilter.setPosition("менеджер");
        mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.data.RESULT", is("success")));
    }

    @Test
    void exceptionFromSave() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setOfficeId(7);
        userFilter.setFirstName("Олег");
        mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }
}