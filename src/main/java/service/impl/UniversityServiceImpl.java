package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.University;
import repository.UniversityRepository;
import service.UniversityService;
import ui.UserMenu;
import util.ApplicationContext;

import static ui.UserMenu.scanner;

public class UniversityServiceImpl extends BaseServiceImpl<University, Long, UniversityRepository> implements UniversityService {

    public UniversityServiceImpl(UniversityRepository repository) {
        super(repository);
    }
    @Override
    public University findByUniversityNumber(String universityNumber) {
        return repository.findByUniversityNumber(universityNumber);
    }
    @Override
    public University findByUniversityNumberChecker(String universityNumber) {
        University university = ApplicationContext.getUniversityService().findByUniversityNumber(universityNumber);

        while (university == null) {
            System.out.println("University not found for the given university number.");

            System.out.print("Enter another university number or 'exit' to quit: ");
            String newUniversityNumber = scanner.nextLine();

            if (newUniversityNumber.equalsIgnoreCase("exit")) {
                UserMenu.run();
            }

            university = ApplicationContext.getUniversityService().findByUniversityNumber(universityNumber);
        }

        return university;
    }
}
