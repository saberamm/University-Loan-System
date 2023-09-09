package service;

import base.service.BaseService;
import entity.University;

public interface UniversityService extends BaseService<University, Long> {
    University findByUniversityNumber(String universityNumber);

    University findByUniversityNumberChecker(String universityNumber);
}
