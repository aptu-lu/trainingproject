package ru.bellintegrator.trainingproject.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.service.user.UserService;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

import java.util.List;

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
    public List<ListUserView> list(@RequestBody @Validated(MarkerValidate.List.class) UserFilter userFilter) {
        return userService.getList(userFilter);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserView getUser(@PathVariable int id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody @Validated(MarkerValidate.Update.class) UserFilter userFilter) {
        userService.update(userFilter);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody @Validated(MarkerValidate.Save.class) UserFilter userFilter) {
        userService.add(userFilter);
    }
}
