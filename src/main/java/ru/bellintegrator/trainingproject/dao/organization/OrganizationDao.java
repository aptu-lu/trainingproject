package ru.bellintegrator.trainingproject.dao.organization;

import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;

import java.util.List;

/**
 * Dao для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить все объекты Organization подходящие по фильтру
     *
     * @param organizationFilter фильтр по организации
     * @return список организаций
     */
    List<Organization> list(OrganizationFilter organizationFilter);

    /**
     * Получить Organization по идентификатору
     *
     * @param id уникальный идентификатор организации
     * @return организация
     */
    Organization loadById(int id);

    /**
     * Обновить Organization
     *
     * @param organizationFilter фильтр по органзиции
     */
    void update(OrganizationFilter organizationFilter);

    /**
     * Сохранить Organization
     *
     * @param organizationFilter фильтр по организации
     */
    void save(OrganizationFilter organizationFilter);
}
