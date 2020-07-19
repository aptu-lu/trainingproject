package ru.bellintegrator.trainingproject.controller.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.trainingproject.service.docs.DocsService;
import ru.bellintegrator.trainingproject.view.ResponseData;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocsController {

    private final DocsService docsService;

    @Autowired
    public DocsController(DocsService docsService) {
        this.docsService = docsService;
    }

    @GetMapping
    public ResponseData list() {
        ResponseData responseData = docsService.list();
        return responseData;
    }
}
