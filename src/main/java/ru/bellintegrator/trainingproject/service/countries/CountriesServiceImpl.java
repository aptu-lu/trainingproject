package ru.bellintegrator.trainingproject.service.countries;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.trainingproject.dao.countries.CountriesDao;
import ru.bellintegrator.trainingproject.model.Countries;
import ru.bellintegrator.trainingproject.view.ResponseData;
import ru.bellintegrator.trainingproject.view.countries.ListCountriesView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements CountriesService {

    private final CountriesDao countriesDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountriesServiceImpl(CountriesDao countriesDao, MapperFacade mapperFacade) {
        this.countriesDao = countriesDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public ResponseData list() {
        List<Countries> list = countriesDao.list();
        List<ListCountriesView> listDocsViews = list.stream()
                .map((countries -> mapperFacade.map(countries, ListCountriesView.class)))
                .collect(Collectors.toList());
        ResponseData responseData = new ResponseData();
        responseData.setData(listDocsViews);
        return responseData;
    }
}
