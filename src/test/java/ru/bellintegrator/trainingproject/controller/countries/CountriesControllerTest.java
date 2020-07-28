package ru.bellintegrator.trainingproject.controller.countries;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bellintegrator.trainingproject.service.countries.CountriesService;
import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CountriesController.class)
@AutoConfigureMockMvc
class CountriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CountriesController countriesController;

    @MockBean
    private CountriesService countriesService;

    @Test
    void getAllCountries() throws Exception {
        ListCountriesView countries = new ListCountriesView();
        countries.setCode("02");
        countries.setName("Россия");
        ListCountriesView countries1 = new ListCountriesView();
        countries1.setCode("110");
        countries1.setName("Америка");
        List<ListCountriesView> countriesList = Arrays.asList(countries, countries1);
        when(countriesService.list()).thenReturn(countriesList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/countries"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0].code", is("02")))
                .andExpect(jsonPath("$.data[0].name", is("Россия")))
                .andExpect(jsonPath("$.data[1].code", is("110")))
                .andExpect(jsonPath("$.data[1].name", is("Америка")));
        verify(countriesService, times(1)).list();
        verifyNoMoreInteractions(countriesService);
    }

//    @Test
//    void exceptionFromGetAll() throws Exception {
//        doThrow(Exception.class).when(countriesService.list());
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/countries"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.error", is("Ошибка сервера.")))
//                .andExpect(jsonPath("$.codeError", is(any(String.class))));
//    }
}