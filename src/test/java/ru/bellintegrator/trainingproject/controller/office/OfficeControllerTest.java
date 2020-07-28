package ru.bellintegrator.trainingproject.controller.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.service.office.OfficeService;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(OfficeController.class)
@AutoConfigureMockMvc
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private OfficeController officeController;

    @MockBean
    private OfficeService officeService;

    @Test
    void list() throws Exception {
        ListOfficeView listOfficeView = new ListOfficeView();
        listOfficeView.setId(5);
        listOfficeView.setName("Космос");
        listOfficeView.setActive(true);
        ListOfficeView listOfficeView2 = new ListOfficeView();
        listOfficeView2.setId(10);
        listOfficeView2.setName("Звезда");
        listOfficeView2.setActive(false);
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setId(3);
        List<ListOfficeView> listOfficeViews = Arrays.asList(listOfficeView,listOfficeView2);
        when(officeService.getList(any(OfficeFilter.class))).thenReturn(listOfficeViews);
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(5)))
                .andExpect(jsonPath("$.data[0].name", is("Космос")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(10)))
                .andExpect(jsonPath("$.data[1].name", is("Звезда")))
                .andExpect(jsonPath("$.data[1].isActive", is(false)));
        verify(officeService, times(1)).getList(any(OfficeFilter.class));
        verifyNoMoreInteractions(officeService);
    }

    @Test
    void getOffice() throws Exception {
        OfficeView officeView = new OfficeView();
        officeView.setId(3);
        officeView.setName("Полет");
        officeView.setAddress("Ленина 2");
        officeView.setPhone("8945954959");
        officeView.setActive(true);
        when(officeService.get(any(Integer.class))).thenReturn(officeView);
        mockMvc.perform(get("/api/office/3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id", is(3)))
                .andExpect(jsonPath("$.data.name", is("Полет")))
                .andExpect(jsonPath("$.data.address", is("Ленина 2")))
                .andExpect(jsonPath("$.data.phone", is("8945954959")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
        verify(officeService, times(1)).get(any(Integer.class));
        verifyNoMoreInteractions(officeService);
    }

    @Test
    void update() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setId(3);
        doNothing().when(officeService).update(any(OfficeFilter.class));
        mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(officeService, times(1)).update(any(OfficeFilter.class));
        verifyNoMoreInteractions(officeService);
    }

    @Test
    void save() throws Exception {
        OfficeFilter officeFilter = new OfficeFilter();
        officeFilter.setOrgId(3);
        doNothing().when(officeService).add(any(OfficeFilter.class));
        mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(officeService,times(1)).add(any(OfficeFilter.class));
        verifyNoMoreInteractions(officeService);
    }
}