package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.CreditCard;
import repository.CreditCardRepository;
import service.CreditCardService;

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {
    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }
}
