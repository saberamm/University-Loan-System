package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Installment;
import jakarta.persistence.EntityManager;
import repository.InstallmentRepository;

public class InstallmentRepositoryImpl extends BaseRepositoryImpl<Installment, Long> implements InstallmentRepository {
    public InstallmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }
}
