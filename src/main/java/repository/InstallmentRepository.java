package repository;

import base.repository.BaseRepository;
import entity.Installment;

import java.util.List;

public interface InstallmentRepository extends BaseRepository<Installment, Long> {
    Installment findByInstallmentNumber(String installmentNumber);

    List<Installment> findByInstallmentsLoanId(Long id);
}
