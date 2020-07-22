package ru.bellintegrator.trainingproject.service.docs;

import ru.bellintegrator.trainingproject.view.docs.ListDocsView;

import java.util.List;

/**
 * Сервис Docs
 */
public interface DocsService {

    /**
     * Получить список документов
     *
     * @return
     */
    List<ListDocsView> list();
}
