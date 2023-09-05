package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.University;
import jakarta.persistence.EntityManager;
import repository.UniversityRepository;

public class UniversityRepositoryImpl extends BaseRepositoryImpl<University, Long> implements UniversityRepository {
    public UniversityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }
}
