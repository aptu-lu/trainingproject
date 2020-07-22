package ru.bellintegrator.trainingproject.service.office;

import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.List;


public interface OfficeService {

    List<ListOfficeView> getList(OfficeFilter officeFilter);

    OfficeView get(int id);

    void update(OfficeFilter officeFilter);

    void add(OfficeFilter officeFilter);
}