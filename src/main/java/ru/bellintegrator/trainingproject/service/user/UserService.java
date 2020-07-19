package ru.bellintegrator.trainingproject.service.user;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.trainingproject.filter.MarkerValidate;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.view.ResponseData;

public interface UserService {

    ResponseData getList(@Validated(MarkerValidate.List.class) UserFilter userFilter);

    ResponseData get(int id);

    ResponseData update(@Validated(MarkerValidate.Update.class) UserFilter userFilter);

    ResponseData add(@Validated(MarkerValidate.Save.class) UserFilter userFilter);
}
