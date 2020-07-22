package ru.bellintegrator.trainingproject.dao.docs;

import ru.bellintegrator.trainingproject.model.Docs;

import java.util.List;

/**
 * Dao для работы с Docs
 */
public interface DocsDao {

    /**
     * Получить все объекты Docs
     *
     * @return список документов
     */
    List<Docs> list();

    /**
     * Получить Docs по названию
     *
     * @param name название документа
     * @return документ
     */
    Docs loadByName(String name);
}
