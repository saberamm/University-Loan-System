package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.CreditCard;
import jakarta.persistence.EntityManager;
import repository.CreditCardRepository;

public class CreditCardRepositoryImpl extends BaseRepositoryImpl<CreditCard, Long> implements CreditCardRepository {
    public CreditCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return null;
    }
}
