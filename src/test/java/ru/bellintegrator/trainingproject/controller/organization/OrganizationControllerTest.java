package ru.bellintegrator.trainingproject.controller.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.service.organization.OrganizationService;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(OrganizationController.class)
@AutoConfigureMockMvc
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private OrganizationController organizationController;

    @MockBean
    private OrganizationService organizationService;

    @Test
    void list() throws Exception {
        ListOrganizationView listOrganizationView = new ListOrganizationView();
        listOrganizationView.setId(2);
        listOrganizationView.setName("Юпитер");
        listOrganizationView.setActive(true);
        ListOrganizationView listOrganizationView2 = new ListOrganizationView();
        listOrganizationView2.setId(1);
        listOrganizationView2.setName("Берег");
        listOrganizationView2.setActive(false);
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setId(3);
        List<ListOrganizationView> listOrganizationViews = Arrays.asList(listOrganizationView,listOrganizationView2);
        when(organizationService.getList(any(OrganizationFilter.class))).thenReturn(listOrganizationViews);
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].name", is("Юпитер")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(1)))
                .andExpect(jsonPath("$.data[1].name", is("Берег")))
                .andExpect(jsonPath("$.data[1].isActive", is(false)));
        verify(organizationService, times(1)).getList(any(OrganizationFilter.class));
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    void getOrganization() throws Exception {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(1);
        organizationView.setName("Юпитер");
        organizationView.setAddress("Ленина 2");
        organizationView.setPhone("8945954959");
        organizationView.setActive(false);
        when(organizationService.get(any(Integer.class))).thenReturn(organizationView);
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Юпитер")))
                .andExpect(jsonPath("$.data.fullName", is(nullValue())))
                .andExpect(jsonPath("$.data.inn", is(nullValue())))
                .andExpect(jsonPath("$.data.kpp", is(nullValue())))
                .andExpect(jsonPath("$.data.address", is("Ленина 2")))
                .andExpect(jsonPath("$.data.phone", is("8945954959")))
                .andExpect(jsonPath("$.data.isActive", is(false)));
        verify(organizationService, times(1)).get(any(Integer.class));
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    void update() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setId(3);
        doNothing().when(organizationService).update(any(OrganizationFilter.class));
        mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(organizationService, times(1)).update(any(OrganizationFilter.class));
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    void save() throws Exception {
        OrganizationFilter organizationFilter = new OrganizationFilter();
        organizationFilter.setId(3);
        doNothing().when(organizationService).add(any(OrganizationFilter.class));
        mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(organizationService,times(1)).add(any(OrganizationFilter.class));
        verifyNoMoreInteractions(organizationService);
    }
}