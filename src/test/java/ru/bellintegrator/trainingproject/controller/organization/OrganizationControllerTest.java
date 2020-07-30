package ru.bellintegrator.trainingproject.controller.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void list() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setName("ООО \"РЕСУРС\"");
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(1)))
                .andExpect(jsonPath("$.data[0]", aMapWithSize(3)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].name", is("ООО \"РЕСУРС\"")))
                .andExpect(jsonPath("$.data[0].isActive", is(false)));
    }

    @Test
    void exceptionFromList() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setName("ООО \"Путь\"");
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void getOrganization() throws Exception {
        mockMvc.perform(get("/api/organization/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",aMapWithSize(8)))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.name", is("ООО \"РЕСУРС\"")))
                .andExpect(jsonPath("$.data.fullName", is("Общество с ограниченной ответственностью \"Ресурс\"")))
                .andExpect(jsonPath("$.data.inn", is("754839403230")))
                .andExpect(jsonPath("$.data.kpp", is("334433212")))
                .andExpect(jsonPath("$.data.address", is("ул. Мира 7б")))
                .andExpect(jsonPath("$.data.phone", is(nullValue())))
                .andExpect(jsonPath("$.data.isActive", is(false)));
    }

    @Test
    void exceptionFromGetOrganization() throws Exception {
        mockMvc.perform(get("/api/organization/5"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void update() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setId(1);
        organizationFilter.setName("ООО Беларусь");
        organizationFilter.setFullName("Общество с ограниченной ответственностью Беларусь");
        organizationFilter.setInn("1234234234");
        organizationFilter.setKpp("223423432");
        organizationFilter.setAddress("ул. Злобина 44");
        mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.RESULT", is("success")));
    }

    @Test
    void exceptionFromUpdate() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setId(5);
        organizationFilter.setName("ООО Ладога");
        organizationFilter.setFullName("Общество с ограниченной ответственностью Ладога");
        organizationFilter.setInn("456464565");
        organizationFilter.setKpp("5444564564");
        mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }

    @Test
    void save() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setName("ООО \"Восход\"");
        organizationFilter.setFullName("Общество с ограниченной ответственностью \"Восход\"");
        organizationFilter.setInn("45645434533");
        organizationFilter.setKpp("2345768994");
        organizationFilter.setAddress("ул. Лермонтова 31");
        mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)))
                .andExpect(jsonPath("$.RESULT", is("success")));
    }

    @Test
    void exceptionFromSave() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setInn("45645434533");
        organizationFilter.setKpp("2345768994");
        mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(2)))
                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
                .andExpect(jsonPath("$.codeError", notNullValue()));
    }
}