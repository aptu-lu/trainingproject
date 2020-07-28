package ru.bellintegrator.trainingproject.dao.organization;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.filter.OrganizationFilter;
import ru.bellintegrator.trainingproject.model.Organization;
import ru.bellintegrator.trainingproject.model.Organization_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager, MapperFacade mapperFacade) {
        this.entityManager = entityManager;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(OrganizationFilter organizationFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> builderQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = builderQuery.from(Organization.class);
        builderQuery.select(organizationRoot);
        Predicate criteria = getPredicate(organizationFilter, criteriaBuilder, organizationRoot);
        builderQuery.where(criteria);
        return entityManager.createQuery(builderQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(int id) {
        String LOAD_BY_ID = "SELECT o FROM Organization o WHERE o.id = :id";
        TypedQuery<Organization> query = entityManager.createQuery(LOAD_BY_ID, Organization.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OrganizationFilter organizationFilter) {
        Organization organization = loadById(organizationFilter.getId());
        updateFields(organization, organizationFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OrganizationFilter organizationFilter) {
        Organization organization = mapperFacade.map(organizationFilter, Organization.class);
        entityManager.persist(organization);
    }

    private Predicate getPredicate(OrganizationFilter organizationFilter, CriteriaBuilder criteriaBuilder, Root<Organization> organizationRoot) {
        Predicate criteria = criteriaBuilder.and((criteriaBuilder.equal(organizationRoot.get(Organization_.name), organizationFilter.getName())));
        if (organizationFilter.getInn() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(organizationRoot.get(Organization_.inn), organizationFilter.getInn()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (organizationFilter.getActive() != null) {
            Predicate isActivePredicate = criteriaBuilder.and(criteriaBuilder.equal(organizationRoot.get(Organization_.isActive), organizationFilter.getActive()));
            criteria = criteriaBuilder.and(criteria, isActivePredicate);
        }
        return criteria;
    }

    private void updateFields(Organization organization, OrganizationFilter organizationFilter) {
        organization.setName(organizationFilter.getName());
        organization.setFullName(organizationFilter.getFullName());
        organization.setInn(organizationFilter.getInn());
        organization.setKpp(organizationFilter.getKpp());
        organization.setAddress(organizationFilter.getAddress());
        if (organizationFilter.getPhone() != null) {
            organization.setPhone(organizationFilter.getPhone());
        }
        if (organizationFilter.getActive()) {
            organization.setActive(organizationFilter.getActive());
        }
    }
}
