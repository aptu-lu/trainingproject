package ru.bellintegrator.trainingproject.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.model.Organization_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;

    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Organization> list(OrganizationFilter organizationFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> builderQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = builderQuery.from(Organization.class);
        builderQuery.select(organizationRoot);
        Predicate criteria = criteriaBuilder.and((criteriaBuilder.equal(organizationRoot.get(Organization_.name), organizationFilter.getName())));
        if (organizationFilter.getInn() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(organizationRoot.get(Organization_.inn), organizationFilter.getInn()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (organizationFilter.getActive() != null) {
            Predicate isActivePredicate = criteriaBuilder.and(criteriaBuilder.equal(organizationRoot.get(Organization_.isActive), organizationFilter.getActive()));
            criteria = criteriaBuilder.and(criteria, isActivePredicate);
        }
        builderQuery.where(criteria);
        return entityManager.createQuery(builderQuery).getResultList();
    }

    @Override
    public Organization loadById(int id) {
        String LOAD_BY_ID = "SELECT o FROM Organization o WHERE o.id = :id";
        TypedQuery<Organization> query = entityManager.createQuery(LOAD_BY_ID, Organization.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(OrganizationFilter organizationFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Organization> builderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Organization.class);
        Root<Organization> organizationRoot = builderCriteriaUpdate.from(Organization.class);
        builderCriteriaUpdate.where(criteriaBuilder.equal(organizationRoot.get(Organization_.id), organizationFilter.getId()));
        builderCriteriaUpdate.set(organizationRoot.get(Organization_.name), organizationFilter.getName())
                .set(organizationRoot.get(Organization_.FULL_NAME), organizationFilter.getFullName())
                .set(organizationRoot.get(Organization_.inn), organizationFilter.getInn())
                .set(organizationRoot.get(Organization_.kpp), organizationFilter.getKpp())
                .set(organizationRoot.get(Organization_.address), organizationFilter.getAddress());
        if (organizationFilter.getPhone() != null) {
            builderCriteriaUpdate.set(organizationRoot.get(Organization_.phone), organizationFilter.getPhone());
        }
        if (organizationFilter.getActive() != null) {
            builderCriteriaUpdate.set(organizationRoot.get(Organization_.isActive), organizationFilter.getActive());
        }
        int rowsUpdated = entityManager.createQuery(builderCriteriaUpdate).executeUpdate();
        if (rowsUpdated != 1) {
            //todo
        }
    }

    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }
}
