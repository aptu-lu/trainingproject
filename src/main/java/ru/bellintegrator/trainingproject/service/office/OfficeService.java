package ru.bellintegrator.trainingproject.service.office;

import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.List;

/**
 * Сервис Office
 */
public interface OfficeService {

    /**
     * Получить список офисов по фильтру
     *
     * @param officeFilter
     * @return
     */
    List<ListOfficeView> getList(OfficeFilter officeFilter);

    /**
     * Получить офис по идентификатору
     *
     * @param id
     * @return
     */
    OfficeView get(int id);

    /**
     * Обновить офис
     *
     * @param officeFilter
     */
    void update(OfficeFilter officeFilter);

    /**
     * Сохранить офис
     * @param officeFilter
     */
    void add(OfficeFilter officeFilter);
}