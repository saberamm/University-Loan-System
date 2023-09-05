package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }
}
