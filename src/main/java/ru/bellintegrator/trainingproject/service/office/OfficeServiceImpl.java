package ru.bellintegrator.trainingproject.service.office;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.office.OfficeDao;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.view.office.ListOfficeView;
import ru.bellintegrator.trainingproject.view.office.OfficeView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ListOfficeView> getList(OfficeFilter officeFilter) {
        List<Office> list = officeDao.list(officeFilter);
        return list.stream()
                .map((office -> mapperFacade.map(office, ListOfficeView.class)))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView get(int id) {
        Office office = officeDao.loadById(id);
        return mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeFilter officeFilter) {
        officeDao.update(officeFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeFilter officeFilter) {
        officeDao.save(officeFilter);
    }
}
