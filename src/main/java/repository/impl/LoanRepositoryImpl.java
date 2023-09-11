package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Loan;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import repository.LoanRepository;

import java.util.List;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan, Long> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public List<Loan> getLoansByNationalCode(String nationalCode) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> query = criteriaBuilder.createQuery(Loan.class);
        Root<Loan> loanRoot = query.from(Loan.class);
        Join<Loan, Student> studentJoin = loanRoot.join("student");

        Predicate predicate = criteriaBuilder.equal(studentJoin.get("nationalCode"), nationalCode);

        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Loan> getLoansByStudentNumber(String studentNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> query = criteriaBuilder.createQuery(Loan.class);
        Root<Loan> loanRoot = query.from(Loan.class);
        Join<Loan, Student> studentJoin = loanRoot.join("student");

        Predicate predicate = criteriaBuilder.equal(studentJoin.get("studentNumber"), studentNumber);

        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }
}
