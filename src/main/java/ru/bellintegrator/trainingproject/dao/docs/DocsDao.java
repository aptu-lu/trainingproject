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
     * @return
     */
    List<Docs> list();

    /**
     * Получить Docs по названию
     *
     * @param name
     * @return
     */
    Docs loadByName(String name);
}
