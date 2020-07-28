package ru.bellintegrator.trainingproject.controller.docs;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bellintegrator.trainingproject.service.docs.DocsService;
import ru.bellintegrator.trainingproject.view.docs.ListDocsView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DocsController.class)
@AutoConfigureMockMvc
class DocsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DocsController docsController;

    @MockBean
    private DocsService docsService;

    @Test
    void getAllDocs() throws Exception {
        ListDocsView docs = new ListDocsView();
        docs.setCode("111");
        docs.setName("Паспорт");
        ListDocsView docs2 = new ListDocsView();
        docs2.setCode("202");
        docs2.setName("Военный билет");
        List<ListDocsView> docsList = Arrays.asList(docs, docs2);
        when(docsService.list()).thenReturn(docsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docs"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0].code", is("111")))
                .andExpect(jsonPath("$.data[0].name", is("Паспорт")))
                .andExpect(jsonPath("$.data[1].code", is("202")))
                .andExpect(jsonPath("$.data[1].name", is("Военный билет")));
        verify(docsService, times(1)).list();
        verifyNoMoreInteractions(docsService);
    }
}