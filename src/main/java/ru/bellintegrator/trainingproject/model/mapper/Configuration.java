package ru.bellintegrator.trainingproject.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.model.User;

@Component
public class Configuration extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Organization.class, OrganizationFilter.class).byDefault().register();
        factory.classMap(Office.class, OfficeFilter.class).byDefault().register();
        factory.classMap(User.class, UserFilter.class).byDefault().register();
    }
}
