package ru.bellintegrator.trainingproject.dao.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.model.Countries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountriesDaoImpl implements CountriesDao{

    private final EntityManager entityManager;

    @Autowired
    public CountriesDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Countries> list() {
        String LOAD_BY_ID = "SELECT c FROM Countries c";
        TypedQuery<Countries> query = entityManager.createQuery(LOAD_BY_ID, Countries.class);
        return query.getResultList();
    }

    @Override
    public Countries loadByCode(String code) {
        String LOAD_BY_CODE = "SELECT c FROM Countries c WHERE c.code = :code";
        TypedQuery<Countries> query = entityManager.createQuery(LOAD_BY_CODE, Countries.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
