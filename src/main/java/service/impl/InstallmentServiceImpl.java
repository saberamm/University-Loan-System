package service.impl;


import base.service.impl.BaseServiceImpl;
import entity.Installment;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.util.List;

public class InstallmentServiceImpl extends BaseServiceImpl<Installment, Long, InstallmentRepository> implements InstallmentService {
    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }

    @Override
    public Installment findByInstallmentNumber(String installmentNumber) {
        return repository.findByInstallmentNumber(installmentNumber);
    }

    @Override
    public List<Installment> findByInstallmentsLoanId(Long id) {
        return repository.findByInstallmentsLoanId(id);
    }
}
