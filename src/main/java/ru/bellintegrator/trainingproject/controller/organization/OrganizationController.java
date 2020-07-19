package ru.bellintegrator.trainingproject.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.service.organization.OrganizationService;
import ru.bellintegrator.trainingproject.view.ResponseData;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/organization/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData list(OrganizationFilter organizationFilter) {
        ResponseData responseData = organizationService.getList(organizationFilter);
        return responseData;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData getOrganization(@PathVariable int id) {
        ResponseData responseData = organizationService.get(id);
        return responseData;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(OrganizationFilter organizationFilter) {
        ResponseData responseData = organizationService.update(organizationFilter);
        return responseData;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseData save(OrganizationFilter organizationFilter) {
        ResponseData responseData = organizationService.add(organizationFilter);
        return responseData;
    }
}
