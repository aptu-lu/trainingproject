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
     * @param organizationFilter
     * @return
     */
    List<Organization> list(OrganizationFilter organizationFilter);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(int id);

    /**
     * Обновить Organization
     *
     * @param organizationFilter
     */
    void update(OrganizationFilter organizationFilter);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void save(Organization organization);
}
