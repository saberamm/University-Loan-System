package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Installment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import repository.InstallmentRepository;

public class InstallmentRepositoryImpl extends BaseRepositoryImpl<Installment, Long> implements InstallmentRepository {
    public InstallmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }
    @Override
    public Installment findByInstallmentNumber(String installmentNumber) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Installment> criteriaQuery = builder.createQuery(Installment.class);
        Root<Installment> root = criteriaQuery.from(Installment.class);

        criteriaQuery.select(root).where(builder.equal(root.get("installmentNumber"), installmentNumber));

        return entityManager.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
    }
}
