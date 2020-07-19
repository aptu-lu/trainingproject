package ru.bellintegrator.trainingproject.dao.organization;

import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;

import java.util.List;

public interface OrganizationDao {

    List<Organization> list(OrganizationFilter organizationFilter);

    Organization loadById(int id);

    void update(OrganizationFilter organizationFilter);

    void save(Organization organization);
}
