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
     * @param officeFilter фильтр по офису
     * @return список представлений офиса
     */
    List<ListOfficeView> getList(OfficeFilter officeFilter);

    /**
     * Получить офис по идентификатору
     *
     * @param id уникальный идентификатор офиса
     * @return представление офиса
     */
    OfficeView get(int id);

    /**
     * Обновить офис
     *
     * @param officeFilter фильтр по офису
     */
    void update(OfficeFilter officeFilter);

    /**
     * Сохранить офис
     * @param officeFilter фильтр по офису
     */
    void add(OfficeFilter officeFilter);
}