package ru.bellintegrator.trainingproject.dao.user;

import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.User;

import java.util.List;

/**
 * Dao для работы с пользователем
 */
public interface UserDao {

    /**
     * Получить все объекты User подходящие по фильтру
     *
     * @param userFilter
     * @return
     */
    List<User> list(UserFilter userFilter);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(int id);

    /**
     * Обновить User
     *
     * @param userFilter
     */
    void update(UserFilter userFilter);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);
}
