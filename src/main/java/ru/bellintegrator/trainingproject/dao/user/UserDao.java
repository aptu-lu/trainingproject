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
     * @param userFilter фильтр по по пользователю
     * @return список пользователей
     */
    List<User> list(UserFilter userFilter);

    /**
     * Получить User по идентификатору
     *
     * @param id уникальный идентификатор пользователя
     * @return пользователь
     */
    User loadById(int id);

    /**
     * Обновить User
     *
     * @param userFilter фильтр по пользователю
     */
    void update(UserFilter userFilter);

    /**
     * Сохранить User
     *
     * @param user пользователь
     */
    void save(User user);
}
