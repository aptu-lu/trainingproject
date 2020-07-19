package ru.bellintegrator.trainingproject.service.office;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.office.OfficeDao;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.view.ResponseData;
import ru.bellintegrator.trainingproject.view.SuccessResult;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public ResponseData getList(OfficeFilter officeFilter) {
        List<Office> list = officeDao.list(officeFilter);
        List<ListOfficeView> listOfficeViews = list.stream()
                .map((office -> mapperFacade.map(office, ListOfficeView.class)))
                .collect(Collectors.toList());
        ResponseData responseData = new ResponseData();
        responseData.setData(listOfficeViews);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData get(int id) {
        Office office = officeDao.loadById(id);
        OfficeView officeView = mapperFacade.map(office, OfficeView.class);
        ResponseData responseData = new ResponseData();
        responseData.setData(officeView);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData update(OfficeFilter officeFilter) {
        officeDao.update(officeFilter);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData add(OfficeFilter officeFilter) {
        Office office = mapperFacade.map(officeFilter, Office.class);
        officeDao.save(office);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }
}
