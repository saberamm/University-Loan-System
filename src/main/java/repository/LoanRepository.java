package repository;

import base.repository.BaseRepository;
import entity.Loan;

import java.util.List;

public interface LoanRepository extends BaseRepository<Loan, Long> {
    List<Loan> getLoansByNationalCode(String spouseNationalCode);

    List<Loan> getLoansByStudentNumber(String studentNumber);
}
