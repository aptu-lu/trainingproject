package ru.bellintegrator.trainingproject.dao.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.model.Docs;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocsDaoImpl implements DocsDao {

    private final EntityManager entityManager;

    @Autowired
    public DocsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Docs> list() {
        String LOAD_BY_ID = "SELECT d FROM Docs d";
        TypedQuery<Docs> query = entityManager.createQuery(LOAD_BY_ID, Docs.class);
        return query.getResultList();
    }
}
