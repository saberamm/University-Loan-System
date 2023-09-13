package service;

import base.service.BaseService;
import entity.Installment;

import java.util.List;

public interface InstallmentService extends BaseService<Installment, Long> {
    Installment findByInstallmentNumber(String installmentNumber);

    List<Installment> findByInstallmentsLoanId(Long id);
}
