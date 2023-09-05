package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Loan;
import repository.LoanRepository;
import service.LoanService;

public class LoanServiceImpl extends BaseServiceImpl<Loan, Long, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }
}
