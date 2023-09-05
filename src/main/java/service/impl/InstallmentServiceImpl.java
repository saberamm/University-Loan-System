package service.impl;


import base.service.impl.BaseServiceImpl;
import entity.Installment;
import repository.InstallmentRepository;
import service.InstallmentService;

public class InstallmentServiceImpl extends BaseServiceImpl<Installment, Long, InstallmentRepository> implements InstallmentService {
    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }
}
