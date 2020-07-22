package ru.bellintegrator.trainingproject.dao.countries;

import ru.bellintegrator.trainingproject.model.Countries;

import java.util.List;

/**
 * Dao для работы с Countries
 */
public interface CountriesDao {

    /**
     * Получить все объекты Countries
     *
     * @return список стран
     */
    List<Countries> list();

    /**
     * Получить Countries по коду
     *
     * @param code код страны
     * @return страна
     */
    Countries loadByCode(String code);
}
