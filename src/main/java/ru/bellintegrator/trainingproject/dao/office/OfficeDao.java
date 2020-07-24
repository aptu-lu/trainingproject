package ru.bellintegrator.trainingproject.dao.office;

import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.model.Office;

import java.util.List;

/**
 * Dao для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office подходящие по фильтру
     *
     * @param officeFilter фильтр по офису
     * @return список офисов
     */
    List<Office> list(OfficeFilter officeFilter);

    /**
     * Получить Office по идентификатору
     *
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    Office loadById(int id);

    /**
     * Обновить Office
     *
     * @param officeFilter фильтр по офису
     */
    void update(OfficeFilter officeFilter);

    /**
     * Сохранить Office
     *
     * @param officeFilter офис по офису
     */
    void save(OfficeFilter officeFilter);
}