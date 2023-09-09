package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import repository.UniversityRepository;

public class UniversityRepositoryImpl extends BaseRepositoryImpl<University, Long> implements UniversityRepository {
    public UniversityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }
    @Override
    public University findByUniversityNumber(String universityNumber) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<University> criteriaQuery = builder.createQuery(University.class);
        Root<University> root = criteriaQuery.from(University.class);

        criteriaQuery.select(root).where(builder.equal(root.get("universityNumber"), universityNumber));

        return entityManager.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
    }
}
