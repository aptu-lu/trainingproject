package ru.bellintegrator.trainingproject.service.user;

import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

import java.util.List;

/**
 * Сервис User
 */
public interface UserService {

    /**
     * Получить список пользователей по фильтру
     *
     * @param userFilter фильтр по пользователю
     * @return список представлений пользователя
     */
    List<ListUserView> getList(UserFilter userFilter);

    /**
     * Получить пользователя по идентификатору
     *
     * @param id уникальный идентификатор пользователя
     * @return представление пользователя
     */
    UserView get(int id);

    /**
     * Обновить пользователя
     *
     * @param userFilter фильтр по пользователю
     */
    void update(UserFilter userFilter);

    /**
     * Сохранить пользователя
     *
     * @param userFilter фильтр по пользователю
     */
    void add(UserFilter userFilter);
}
