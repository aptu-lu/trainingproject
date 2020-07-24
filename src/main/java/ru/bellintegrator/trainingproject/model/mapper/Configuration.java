package ru.bellintegrator.trainingproject.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.Countries;
import ru.bellintegrator.trainingproject.model.Docs;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.model.User;
import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;
import ru.bellintegrator.trainingproject.view.docs.ListDocsView;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;
import ru.bellintegrator.trainingproject.view.organization.ListOrganizationView;
import ru.bellintegrator.trainingproject.view.organization.OrganizationView;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

@Component
public class Configuration extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Countries.class, ListCountriesView.class);

        factory.classMap(Docs.class, ListDocsView.class);

        factory.classMap(Office.class, ListOfficeView.class).byDefault().register();
        factory.classMap(Office.class, OfficeView.class).byDefault().register();
        factory.classMap(OfficeFilter.class, Office.class).byDefault().register();

        factory.classMap(Organization.class, ListOrganizationView.class).byDefault().register();
        factory.classMap(Organization.class, OrganizationView.class).byDefault().register();
        factory.classMap(OrganizationFilter.class, Organization.class).byDefault().register();

        factory.classMap(User.class, ListUserView.class).byDefault().register();
        factory.classMap(User.class, UserView.class).byDefault().register();
        factory.classMap(UserFilter.class, User.class).byDefault().register();
    }
}
