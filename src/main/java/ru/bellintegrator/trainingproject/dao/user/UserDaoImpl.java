package ru.bellintegrator.trainingproject.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.dao.countries.CountriesDao;
import ru.bellintegrator.trainingproject.dao.docs.DocsDao;
import ru.bellintegrator.trainingproject.dao.office.OfficeDao;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;
    private final OfficeDao officeDao;
    private final DocsDao docsDao;
    private final CountriesDao countriesDao;

    @Autowired
    public UserDaoImpl(EntityManager entityManager, OfficeDao officeDao, DocsDao docsDao, CountriesDao countriesDao) {
        this.entityManager = entityManager;
        this.officeDao = officeDao;
        this.docsDao = docsDao;
        this.countriesDao = countriesDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> list(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> builderQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = builderQuery.from(User.class);
        builderQuery.select(userRoot);
        Predicate criteria = getPredicate(userFilter, criteriaBuilder, userRoot);
        builderQuery.where(criteria);
        List<User> resultList = entityManager.createQuery(builderQuery).getResultList();
        if (resultList.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(int id) {
        String LOAD_BY_ID = "SELECT u FROM User u LEFT JOIN FETCH u.office " +
                "LEFT JOIN FETCH u.countries " +
                "LEFT JOIN FETCH u.userDoc ud " +
                "LEFT JOIN FETCH ud.docs " +
                "WHERE u.id = :id";
        TypedQuery<User> query = entityManager.createQuery(LOAD_BY_ID, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserFilter userFilter) {
        User user = loadById(userFilter.getId());
        updateFields(user, userFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserFilter userFilter) {
        User user = createUser(userFilter);
        entityManager.persist(user);
    }

    private Predicate getPredicate(UserFilter userFilter, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
        Predicate criteria = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.office).get(Office_.id), userFilter.getOfficeId()));
        if (userFilter.getFirstName() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.firstName), userFilter.getFirstName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getLastName() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.lastName), userFilter.getLastName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getMiddleName() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.middleName), userFilter.getMiddleName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getPosition() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.position), userFilter.getPosition()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getDocName() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.userDoc)
                    .get(UserDoc_.docs).get(Docs_.name), userFilter.getDocName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getCitizenshipCode() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(userRoot.get(User_.countries)
                    .get(Countries_.code), userFilter.getCitizenshipCode()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        return criteria;
    }

    private void updateFields(User user, UserFilter userFilter) {
        user.setFirstName(userFilter.getFirstName());
        user.setPosition(userFilter.getPosition());
        if (userFilter.getOfficeId() != null) {
            Office office = officeDao.loadById(userFilter.getOfficeId());
            user.setOffice(office);
        }
        if (userFilter.getLastName() != null) {
            user.setLastName(userFilter.getLastName());
        }
        if (userFilter.getMiddleName() != null) {
            user.setMiddleName(userFilter.getMiddleName());
        }

        if (userFilter.getPhone() != null) {
            user.setPhone(userFilter.getPhone());
        }
        if (userFilter.getDocName() != null) {
            Docs docs = docsDao.loadByName(userFilter.getDocName());
            user.getUserDoc().setDocs(docs);
        }
        if (userFilter.getDocNumber() != null) {
            user.getUserDoc().setDocNumber(userFilter.getDocNumber());
        }
        if (userFilter.getDocDate() != null) {
            user.getUserDoc().setDocDate(userFilter.getDocDate());
        }
        if (userFilter.getCitizenshipCode() != null) {
            Countries countries = countriesDao.loadByCode(userFilter.getCitizenshipCode());
            user.setCountries(countries);
        }
        if (userFilter.getIdentified() != null) {
            user.setIdentified(userFilter.getIdentified());
        }
    }

    private User createUser(UserFilter userFilter) {
        User user = new User();
        Office office = officeDao.loadById(userFilter.getOfficeId());
        user.setOffice(office);
        user.setFirstName(userFilter.getFirstName());
        user.setPosition(userFilter.getPosition());
        if (userFilter.getLastName() != null) {
            user.setLastName(userFilter.getLastName());
        }
        if (userFilter.getMiddleName() != null) {
            user.setMiddleName(userFilter.getMiddleName());
        }
        if (userFilter.getPhone() != null) {
            user.setPhone(userFilter.getPhone());
        }
        if (userFilter.getIdentified() != null) {
            user.setIdentified(userFilter.getIdentified());
        }
        if (userFilter.getDocCode() != null || userFilter.getDocName() != null
                || userFilter.getDocNumber() != null || userFilter.getDocDate() != null) {
            UserDoc userDoc = new UserDoc();
            if (userFilter.getDocCode() != null) {
                Docs docs = docsDao.loadByCode(userFilter.getDocCode());
                userDoc.setDocs(docs);
            }
            if (userFilter.getDocName() != null) {
                Docs docs = docsDao.loadByName(userFilter.getDocName());
                userDoc.setDocs(docs);
            }
            if (userFilter.getDocNumber() != null) {
                userDoc.setDocNumber(userFilter.getDocNumber());
            }
            if (userFilter.getDocDate() != null) {
                userDoc.setDocDate(userFilter.getDocDate());
            }
            user.setUserDoc(userDoc);
        }
        if (userFilter.getCitizenshipCode() != null) {
            Countries countries = countriesDao.loadByCode(userFilter.getCitizenshipCode());
            user.setCountries(countries);
        }
        return user;
    }
}
