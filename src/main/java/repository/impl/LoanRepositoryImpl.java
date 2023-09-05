package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Loan;
import jakarta.persistence.EntityManager;
import repository.LoanRepository;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan, Long> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
