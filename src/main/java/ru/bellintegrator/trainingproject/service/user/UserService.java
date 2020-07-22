package ru.bellintegrator.trainingproject.service.user;

import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

import java.util.List;

public interface UserService {

    List<ListUserView> getList(UserFilter userFilter);

    UserView get(int id);

    void update(UserFilter userFilter);

    void add(UserFilter userFilter);
}
