package ru.bellintegrator.trainingproject.dao.user;

import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.User;

import java.util.List;

public interface UserDao {

    List<User> list(UserFilter userFilter);

    User loadById(int id);

    void Update(UserFilter userFilter);

    void Save(User user);
}
