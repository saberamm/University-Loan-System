package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Loan;
import repository.LoanRepository;
import service.LoanService;

import java.util.List;

public class LoanServiceImpl extends BaseServiceImpl<Loan, Long, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }

    @Override
    public List<Loan> getLoansByNationalCode(String spouseNationalCode) {
        return repository.getLoansByNationalCode(spouseNationalCode);
    }

    @Override
    public List<Loan> getLoansByStudentNumber(String studentNumber) {
        return repository.getLoansByStudentNumber(studentNumber);
    }
}
