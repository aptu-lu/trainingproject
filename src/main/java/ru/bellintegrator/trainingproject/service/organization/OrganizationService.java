package ru.bellintegrator.trainingproject.service.organization;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.view.ResponseData;

@Validated
public interface OrganizationService {

    ResponseData getList(@Validated(MarkerValidate.List.class) OrganizationFilter organizationFilter);

    ResponseData get(int id);

    ResponseData update(@Validated(MarkerValidate.Update.class) OrganizationFilter organizationFilter);

    ResponseData add(@Validated(MarkerValidate.Save.class) OrganizationFilter organizationFilter);
}
