package service;

import base.service.BaseService;
import entity.Loan;

import java.util.List;

public interface LoanService extends BaseService<Loan, Long> {
    List<Loan> getLoansByNationalCode(String spouseNationalCode);

    List<Loan> getLoansByStudentNumber(String studentNumber);
}
