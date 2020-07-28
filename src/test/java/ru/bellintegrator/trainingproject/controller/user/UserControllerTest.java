package ru.bellintegrator.trainingproject.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.service.user.UserService;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

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

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void list() throws Exception {
        ListUserView listUserView = new ListUserView();
        listUserView.setId(2);
        listUserView.setFirstName("Андрей");
        listUserView.setPosition("Водитель");
        ListUserView listUserView2 = new ListUserView();
        listUserView2.setId(1);
        listUserView2.setFirstName("Владимир");
        listUserView2.setPosition("Слесарь");
        UserFilter userFilter = new UserFilter();
        userFilter.setId(3);
        List<ListUserView> listUserViews = Arrays.asList(listUserView,listUserView2);
        when(userService.getList(any(UserFilter.class))).thenReturn(listUserViews);
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", iterableWithSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].firstName", is("Андрей")))
                .andExpect(jsonPath("$.data[0].position", is("Водитель")))
                .andExpect(jsonPath("$.data[1].id", is(1)))
                .andExpect(jsonPath("$.data[1].firstName", is("Владимир")))
                .andExpect(jsonPath("$.data[1].position", is("Слесарь")));
        verify(userService, times(1)).getList(any(UserFilter.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void getUser() throws Exception {
        UserView userView = new UserView();
        userView.setId(1);
        userView.setFirstName("Андрей");
        userView.setPosition("Водитель");
        userView.setPhone("8945954959");
        userView.setIdentified(false);
        when(userService.get(any(Integer.class))).thenReturn(userView);
        mockMvc.perform(get("/api/user/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("Андрей")))
                .andExpect(jsonPath("$.data.position", is("Водитель")))
                .andExpect(jsonPath("$.data.phone", is("8945954959")))
                .andExpect(jsonPath("$.data.isIdentified", is(false)));
        verify(userService, times(1)).get(any(Integer.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void update() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setId(3);
        doNothing().when(userService).update(any(UserFilter.class));
        mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(userService, times(1)).update(any(UserFilter.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void save() throws Exception {
        UserFilter userFilter = new UserFilter();
        userFilter.setId(3);
        doNothing().when(userService).add(any(UserFilter.class));
        mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilter)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.RESULT", is("success")));
        verify(userService,times(1)).add(any(UserFilter.class));
        verifyNoMoreInteractions(userService);
    }
}