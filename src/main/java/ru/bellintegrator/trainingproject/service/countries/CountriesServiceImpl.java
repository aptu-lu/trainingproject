package ru.bellintegrator.trainingproject.service.countries;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.countries.CountriesDao;
import ru.bellintegrator.trainingproject.model.Countries;
import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class CountriesServiceImpl implements CountriesService {

    private final CountriesDao countriesDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountriesServiceImpl(CountriesDao countriesDao, MapperFacade mapperFacade) {
        this.countriesDao = countriesDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ListCountriesView> list() {
        List<Countries> list = countriesDao.list();
        return list.stream()
                .map((countries -> mapperFacade.map(countries, ListCountriesView.class)))
                .collect(Collectors.toList());
    }
}
