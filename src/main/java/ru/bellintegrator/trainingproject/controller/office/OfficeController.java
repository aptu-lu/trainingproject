package ru.bellintegrator.trainingproject.controller.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.service.office.OfficeService;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Обрабатывает запросы по заданному URI {@value /api/office}
 */
@RestController
@RequestMapping(value = "/api/office/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Возвращает список представлений офисов по заданному фильтру
     *
     * @param officeFilter фильтр по офису
     * @return список представлений офисов
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<ListOfficeView> list(@RequestBody @Validated(MarkerValidate.List.class) OfficeFilter officeFilter) {
        return officeService.getList(officeFilter);
    }

    /**
     * Возвращает представление офиса по заданному идентификатору
     *
     * @param id идентификатор офиса
     * @return представление офиса
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OfficeView getOffice(@PathVariable int id) {
        return officeService.get(id);
    }

    /**
     * Обновляет офис по заданному фильтру
     *
     * @param officeFilter фильтр по офису
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody @Validated(MarkerValidate.Update.class) OfficeFilter officeFilter) {
        officeService.update(officeFilter);
    }

    /**
     * Сохраняет офис по заданному фильтру
     *
     * @param officeFilter фильтр по офису
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody @Validated({MarkerValidate.Save.class}) OfficeFilter officeFilter) {
        officeService.add(officeFilter);
    }
}