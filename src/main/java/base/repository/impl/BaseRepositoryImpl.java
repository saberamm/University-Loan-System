package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TransactionRequiredException;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<E, ID> {

    protected final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public E save(E e) {
        try {
            entityManager.persist(e);
            return e;
        } catch (PersistenceException ex) {
            throw new RuntimeException("Error while saving entity: " + ex.getMessage(), ex);
        }
    }

    @Override
    public E update(E e) {
        try {
            return entityManager.merge(e);
        } catch (PersistenceException ex) {
            throw new RuntimeException("Error while updating entity: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(E e) {
        try {
            entityManager.remove(e);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("Entity not found");
        } catch (TransactionRequiredException ex) {
            throw new RuntimeException("Transaction is required for delete operation");
        } catch (PersistenceException ex) {
            if (ex.getCause() != null) {
                throw new RuntimeException("Constraint violation occurred during delete operation: " + ex.getMessage(), ex);
            } else {
                throw new RuntimeException("Error while deleting entity: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public E findById(ID id) {
        try {
            return entityManager.find(getEntityClass(), id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public List<E> findAll() {
        try {
            return entityManager.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
        } catch (PersistenceException ex) {
            throw new RuntimeException("Error while fetching entities: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean isContainById(ID id) {
        E ic = entityManager.find(getEntityClass(), id);
        return ic != null;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
