package ru.bellintegrator.trainingproject.service.organization;

import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.List;

/**
 * Сервис Organization
 */
public interface OrganizationService {

    /**
     * Получить список организаций по фильтру
     *
     * @param organizationFilter
     * @return
     */
    List<ListOrganizationView> getList(OrganizationFilter organizationFilter);

    /**
     * Получить организацию по идентификатору
     *
     * @param id
     * @return
     */
    OrganizationView get(int id);

    /**
     * Обновить организацию
     *
     * @param organizationFilter
     */
    void update(OrganizationFilter organizationFilter);

    /**
     * Сохранить организацию
     *
     * @param organizationFilter
     */
    void add(OrganizationFilter organizationFilter);
}
