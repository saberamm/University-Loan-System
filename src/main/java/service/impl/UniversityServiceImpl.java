package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.University;
import repository.UniversityRepository;
import service.UniversityService;

public class UniversityServiceImpl extends BaseServiceImpl<University, Long, UniversityRepository> implements UniversityService {

    public UniversityServiceImpl(UniversityRepository repository) {
        super(repository);
    }
}
