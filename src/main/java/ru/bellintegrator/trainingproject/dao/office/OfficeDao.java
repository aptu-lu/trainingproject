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
     * @param officeFilter
     * @return
     */
    List<Office> list(OfficeFilter officeFilter);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(int id);

    /**
     * Обновить Office
     *
     * @param officeFilter
     */
    void update(OfficeFilter officeFilter);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);
}