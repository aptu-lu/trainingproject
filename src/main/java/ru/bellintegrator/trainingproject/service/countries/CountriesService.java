package ru.bellintegrator.trainingproject.service.countries;

import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;

import java.util.List;

/**
 * Сервис Countries
 */
public interface CountriesService {

    /**
     * Получить спискок стран
     *
     * @return список представлений страны
     */
    List<ListCountriesView> list();
}
