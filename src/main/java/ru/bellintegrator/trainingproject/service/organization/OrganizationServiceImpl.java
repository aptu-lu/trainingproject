package ru.bellintegrator.trainingproject.service.organization;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.organization.OrganizationDao;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ListOrganizationView> getList(OrganizationFilter organizationFilter) {
        List<Organization> list = organizationDao.list(organizationFilter);
        List<ListOrganizationView> listOrganizationViews = list.stream()
                .map((organization -> mapperFacade.map(organization, ListOrganizationView.class)))
                .collect(Collectors.toList());
        return listOrganizationViews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView get(int id) {
        Organization organization = organizationDao.loadById(id);
        OrganizationView organizationView = mapperFacade.map(organization, OrganizationView.class);
        return organizationView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFilter organizationFilter) {
        organizationDao.update(organizationFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationFilter organizationFilter) {
        Organization organization = mapperFacade.map(organizationFilter, Organization.class);
        organizationDao.save(organization);
    }
}
