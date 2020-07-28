package ru.bellintegrator.trainingproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bellintegrator.trainingproject.controller.countries.CountriesController;
import ru.bellintegrator.trainingproject.controller.docs.DocsController;
import ru.bellintegrator.trainingproject.controller.office.OfficeController;
import ru.bellintegrator.trainingproject.controller.organization.OrganizationController;
import ru.bellintegrator.trainingproject.controller.user.UserController;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class TrainingProjectApplicationTests {

	@Autowired
	private CountriesController countriesController;

	@Autowired
	private DocsController docsController;

	@Autowired
	private OrganizationController organizationController;

	@Autowired
	private OfficeController officeController;

	@Autowired
	private UserController userController;

    @Test
	void contextLoads() {
		assertThat(countriesController).isNotNull();
		assertThat(docsController).isNotNull();
		assertThat(organizationController).isNotNull();
		assertThat(officeController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
