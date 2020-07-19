package ru.bellintegrator.trainingproject.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.Countries;
import ru.bellintegrator.trainingproject.model.Countries_;
import ru.bellintegrator.trainingproject.model.Docs;
import ru.bellintegrator.trainingproject.model.Docs_;
import ru.bellintegrator.trainingproject.model.Office;
import ru.bellintegrator.trainingproject.model.Office_;
import ru.bellintegrator.trainingproject.model.User;
import ru.bellintegrator.trainingproject.model.UserDoc;
import ru.bellintegrator.trainingproject.model.UserDoc_;
import ru.bellintegrator.trainingproject.model.User_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> list(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> builderQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = builderQuery.from(User.class);
        Join<User, Office> joinOffice = userRoot.join(User_.office, JoinType.LEFT);
        Join<UserDoc, Docs> joinDocs = userRoot.join(User_.userDoc, JoinType.LEFT).join(UserDoc_.docs);
        Join<User, Countries> joinCountries = userRoot.join(User_.countries, JoinType.LEFT);
        builderQuery.select(userRoot);
        Predicate criteria = criteriaBuilder.and(criteriaBuilder.equal(joinOffice.get(Office_.id), userFilter.getOfficeId()));
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
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(joinDocs.get(Docs_.code), userFilter.getDocName()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        if (userFilter.getCitizenshipCode() != null) {
            Predicate innPredicate = criteriaBuilder.and(criteriaBuilder.equal(joinCountries.get(Countries_.code), userFilter.getCitizenshipCode()));
            criteria = criteriaBuilder.and(criteria, innPredicate);
        }
        builderQuery.where(criteria);
        return entityManager.createQuery(builderQuery).getResultList();
    }

    @Override
    public User loadById(int id) {
        String LOAD_BY_ID = "SELECT u FROM User u LEFT JOIN FETCH u.office " +
                "LEFT JOIN FETCH u.userDoc ud" +
                "LEFT JOIN FETCH ud.docs" +
                "LEFT JOIN FETCH u.countries" +
                "WHERE u.id = :id";
        TypedQuery<User> query = entityManager.createQuery(LOAD_BY_ID, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> builderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = builderCriteriaUpdate.from(User.class);
        Join<User, Office> joinOffice = userRoot.join(User_.office, JoinType.LEFT);
        Join<User, UserDoc> joinUserDoc = userRoot.join(User_.userDoc, JoinType.LEFT);
        Join<UserDoc, Docs> joinDocs = joinUserDoc.join(UserDoc_.docs);
        Join<User, Countries> joinCountries = userRoot.join(User_.countries, JoinType.LEFT);
        builderCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get(User_.id), userFilter.getId()),
                criteriaBuilder.equal(userRoot.get(User_.position), userFilter.getPosition()),
                criteriaBuilder.equal(userRoot.get(User_.firstName), userFilter.getFirstName()));
        if (userFilter.getOfficeId() != null) {
            builderCriteriaUpdate.set(joinOffice.get(Office_.id), userFilter.getOfficeId());
        }
        if (userFilter.getMiddleName() != null) {
            builderCriteriaUpdate.set(userRoot.get(User_.middleName), userFilter.getMiddleName());
        }
        if (userFilter.getLastName() != null) {
            builderCriteriaUpdate.set(userRoot.get(User_.lastName), userFilter.getLastName());
        }
        if (userFilter.getPhone() != null) {
            builderCriteriaUpdate.set(userRoot.get(User_.phone), userFilter.getPhone());
        }
        if (userFilter.getDocName() != null) {
            builderCriteriaUpdate.set(joinDocs.get(Docs_.name), userFilter.getDocName());
        }
        if (userFilter.getDocNumber() != null) {
            builderCriteriaUpdate.set(joinUserDoc.get(UserDoc_.docNumber), userFilter.getDocNumber());
        }
        if (userFilter.getDocDate() != null) {
            builderCriteriaUpdate.set(joinUserDoc.get(UserDoc_.docDate), userFilter.getDocDate());
        }
        if (userFilter.getCitizenshipCode() != null) {
            builderCriteriaUpdate.set(joinCountries.get(Countries_.code), userFilter.getCitizenshipCode());
        }
        if (userFilter.getIdentified() != null) {
            builderCriteriaUpdate.set(userRoot.get(User_.isIdentified), userFilter.getIdentified());
        }
        entityManager.createQuery(builderCriteriaUpdate).executeUpdate();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
