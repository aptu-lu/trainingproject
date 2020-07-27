package ru.bellintegrator.trainingproject.dao.office;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.dao.organization.OrganizationDao;
import ru.bellintegrator.trainingproject.filter.OfficeFilter;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.model.Office_;
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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager entityManager;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.entityManager = entityManager;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(OfficeFilter officeFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> builderQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = builderQuery.from(Office.class);
        builderQuery.select(officeRoot);
        Predicate criteria = criteriaBuilder.and((criteriaBuilder.equal(officeRoot.get(Office_.organization)
                .get(Organization_.id), officeFilter.getOrgId())));
        if (officeFilter.getName() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(officeRoot.get(Office_.name), officeFilter.getName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (officeFilter.getPhone() != null) {
            Predicate isActivePredicate = criteriaBuilder.and(criteriaBuilder.equal(officeRoot.get(Office_.phone), officeFilter.getPhone()));
            criteria = criteriaBuilder.and(criteria, isActivePredicate);
        }
        if (officeFilter.getActive() != null) {
            Predicate isActivePredicate = criteriaBuilder.and(criteriaBuilder.equal(officeRoot.get(Office_.isActive), officeFilter.getActive()));
            criteria = criteriaBuilder.and(criteria, isActivePredicate);
        }
        builderQuery.where(criteria);
        return entityManager.createQuery(builderQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(int id) {
        String LOAD_BY_ID = "SELECT o FROM Office o LEFT JOIN FETCH o.organization WHERE o.id = :id";
        TypedQuery<Office> query = entityManager.createQuery(LOAD_BY_ID, Office.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeFilter officeFilter) {
        Office office = loadById(officeFilter.getId());
        office.setName(officeFilter.getName());
        office.setAddress(officeFilter.getAddress());
        if (officeFilter.getPhone() != null) {
            office.setPhone(officeFilter.getPhone());
        }
        if (officeFilter.getActive() != null) {
            office.setActive(officeFilter.getActive());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OfficeFilter officeFilter) {
        Office office = mapperFacade.map(officeFilter, Office.class);
        Organization organization = organizationDao.loadById(officeFilter.getOrgId());
        office.setOrganization(organization);
        entityManager.persist(office);
    }
}
