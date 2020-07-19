package ru.bellintegrator.trainingproject.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.service.user.UserService;
import ru.bellintegrator.trainingproject.view.ResponseData;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/user/", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData list(UserFilter userFilter) {
        ResponseData responseData = userService.getList(userFilter);
        return responseData;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData getOrganization(@PathVariable int id) {
        ResponseData responseData = userService.get(id);
        return responseData;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(UserFilter userFilter) {
        ResponseData responseData = userService.update(userFilter);
        return responseData;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseData save(UserFilter userFilter) {
        ResponseData responseData = userService.add(userFilter);
        return responseData;
    }
}
