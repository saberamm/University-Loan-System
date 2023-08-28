package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import repository.UserRepository;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User findUserByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.equal(root.get("username"), username));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User userAuthentication(String username, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(
                builder.and(
                        builder.equal(root.get("username"), username),
                        builder.equal(root.get("password"), password)
                )
        );

        try {
            return entityManager.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
        } catch (NoResultException e) {
            return null;
        }
    }
}