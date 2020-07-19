package ru.bellintegrator.trainingproject.controller.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.service.office.OfficeService;
import ru.bellintegrator.trainingproject.view.ResponseData;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/office/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData list(OfficeFilter officeFilter) {
        ResponseData responseData = officeService.getList(officeFilter);
        return responseData;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData getOrganization(@PathVariable int id) {
        ResponseData responseData = officeService.get(id);
        return responseData;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(OfficeFilter officeFilter) {
        ResponseData responseData = officeService.update(officeFilter);
        return responseData;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseData save(OfficeFilter officeFilter) {
        ResponseData responseData = officeService.add(officeFilter);
        return responseData;
    }
}
