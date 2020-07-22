package ru.bellintegrator.trainingproject.service.docs;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.docs.DocsDao;
import ru.bellintegrator.trainingproject.model.Docs;
import ru.bellintegrator.trainingproject.view.docs.ListDocsView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocsServiceImpl implements DocsService {

    private final DocsDao docsDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocsServiceImpl(DocsDao docsDao, MapperFacade mapperFacade) {
        this.docsDao = docsDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public List<ListDocsView> list() {
        List<Docs> list = docsDao.list();
        List<ListDocsView> listDocsViews = list.stream()
                .map((docs -> mapperFacade.map(docs, ListDocsView.class)))
                .collect(Collectors.toList());
        return listDocsViews;
    }
}
