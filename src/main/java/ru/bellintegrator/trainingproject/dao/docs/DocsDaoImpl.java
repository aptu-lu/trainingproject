package ru.bellintegrator.trainingproject.dao.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.model.Docs;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocsDaoImpl implements DocsDao {

    private final EntityManager entityManager;

    @Autowired
    public DocsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Docs> list() {
        String LOAD_BY_ID = "SELECT d FROM Docs d";
        TypedQuery<Docs> query = entityManager.createQuery(LOAD_BY_ID, Docs.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Docs loadByName(String name) {
        String LOAD_BY_NAME = "SELECT d FROM Docs d WHERE d.name = :name";
        TypedQuery<Docs> query = entityManager.createQuery(LOAD_BY_NAME, Docs.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Docs loadByCode(String code) {
        String LOAD_BY_CODE = "SELECT d FROM Docs d WHERE d.code = :code";
        TypedQuery<Docs> query = entityManager.createQuery(LOAD_BY_CODE, Docs.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
