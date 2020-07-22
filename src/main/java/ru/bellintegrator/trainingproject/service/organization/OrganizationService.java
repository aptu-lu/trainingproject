package ru.bellintegrator.trainingproject.service.organization;

import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.List;

public interface OrganizationService {

    List<ListOrganizationView> getList(OrganizationFilter organizationFilter);

    OrganizationView get(int id);

    void update(OrganizationFilter organizationFilter);

    void add(OrganizationFilter organizationFilter);
}
