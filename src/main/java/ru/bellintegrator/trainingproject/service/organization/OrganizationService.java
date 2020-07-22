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
     * @param organizationFilter фильтр по организации
     * @return список представлений организации
     */
    List<ListOrganizationView> getList(OrganizationFilter organizationFilter);

    /**
     * Получить организацию по идентификатору
     *
     * @param id уникальный идентификатор организации
     * @return представление организации
     */
    OrganizationView get(int id);

    /**
     * Обновить организацию
     *
     * @param organizationFilter фильтр по организации
     */
    void update(OrganizationFilter organizationFilter);

    /**
     * Сохранить организацию
     *
     * @param organizationFilter фильтр по организации
     */
    void add(OrganizationFilter organizationFilter);
}
