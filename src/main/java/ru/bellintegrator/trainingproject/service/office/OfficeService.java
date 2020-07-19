package ru.bellintegrator.trainingproject.service.office;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.view.ResponseData;

public interface OfficeService {

    ResponseData getList(@Validated(MarkerValidate.List.class) OfficeFilter officeFilter);

    ResponseData get(int id);

    ResponseData update(@Validated(MarkerValidate.Update.class) OfficeFilter officeFilter);

    ResponseData add(@Validated(MarkerValidate.Save.class) OfficeFilter officeFilter);
}
