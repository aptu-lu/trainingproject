package ru.bellintegrator.trainingproject.controller.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.service.countries.CountriesService;
import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Обрабатывает запросы по заданному URI {@value /api/countries}
 */
@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
public class CountriesController {

    private final CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    /**
     * Возвращает список всех стран
     *
     * @return {@link ListCountriesView} представление списка стран
     */
    @GetMapping
    public List<ListCountriesView> list() {
        return countriesService.list();
    }
}
