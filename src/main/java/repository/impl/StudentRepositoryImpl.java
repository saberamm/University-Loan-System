package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import repository.StudentRepository;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
    @Override
    public Student findByStudentNumber(String studentNumber) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        criteriaQuery.select(root).where(builder.equal(root.get("studentNumber"), studentNumber));

        return entityManager.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
    }
}
