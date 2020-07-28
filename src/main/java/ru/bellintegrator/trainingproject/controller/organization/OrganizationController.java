package ru.bellintegrator.trainingproject.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.service.organization.OrganizationService;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Обрабатывает запросы по заданному URI {@value /api/organization}
 */
@RestController
@RequestMapping(value = "/api/organization/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    /**
     * Сервис
     */
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает список представлений организаций по заданному фильтру
     *
     * @param organizationFilter фильтр по организации
     * @return список представлений организации
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<ListOrganizationView> list(@RequestBody @Validated(MarkerValidate.List.class) OrganizationFilter organizationFilter) {
        return organizationService.getList(organizationFilter);
    }

    /**
     * Возвращает представление организации по заданному фильтру
     *
     * @param id идентификатор организации
     * @return представление организации
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrganizationView getOrganization(@PathVariable int id) {
        return organizationService.get(id);
    }

    /**
     * Обновляет организацию по фильтру
     *
     * @param organizationFilter фильтр по организации
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody @Validated(MarkerValidate.Update.class) OrganizationFilter organizationFilter) {
        organizationService.update(organizationFilter);
    }

    /**
     * Сохраняет организацию по фильтру
     *
     * @param organizationFilter фильтр по организации
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody @Validated(MarkerValidate.Save.class) OrganizationFilter organizationFilter) {
        organizationService.add(organizationFilter);
    }
}
