package ru.bellintegrator.trainingproject.dao.office;

import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.model.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> list(OfficeFilter officeFilter);

    Office loadById(int id);

    void update(OfficeFilter officeFilter);

    void save(Office office);
}