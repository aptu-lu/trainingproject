package ru.bellintegrator.trainingproject.service.organization;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.organization.OrganizationDao;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.view.ResponseData;
import ru.bellintegrator.trainingproject.view.SuccessResult;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public ResponseData getList(OrganizationFilter organizationFilter) {
        List<Organization> list = organizationDao.list(organizationFilter);
        List<ListOrganizationView> listOrganizationViews = list.stream()
                .map((organization -> mapperFacade.map(organization, ListOrganizationView.class)))
                .collect(Collectors.toList());
        ResponseData responseData = new ResponseData();
        responseData.setData(listOrganizationViews);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData get(int id) {
        Organization organization = organizationDao.loadById(id);
        OrganizationView organizationView = mapperFacade.map(organization, OrganizationView.class);
        ResponseData responseData = new ResponseData();
        responseData.setData(organizationView);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData update(OrganizationFilter organizationFilter) {
        organizationDao.update(organizationFilter);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData add(OrganizationFilter organizationFilter) {
        Organization organization = mapperFacade.map(organizationFilter, Organization.class);
        organizationDao.save(organization);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }
}
