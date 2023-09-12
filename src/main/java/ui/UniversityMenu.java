package ui;

import entity.University;
import entity.enumertion.City;
import entity.enumertion.UniversityType;
import util.ApplicationContext;
import validation.TypeValidator;

import static ui.UserMenu.scanner;

public class UniversityMenu {
    public static void addUniversity() {
        University university = new University();
        System.out.print("Enter the university number :");
        university.setUniversityNumber(scanner.next());
        System.out.print("Enter the university name :");
        university.setUniversityName(scanner.next());
        System.out.print("Enter the university type :");
        university.setUniversityType(UniversityType.selectUniversityType());
        if (university.getUniversityType().equals(UniversityType.DOLATI)) {
            System.out.println("is this university daily?");
            university.setDaily(TypeValidator.getBooleanInput());
        }
        System.out.println("Enter the university city :");
        university.setCity(City.selectCity());
        if (ApplicationContext.getUniversityService().findByUniversityNumber(university.getUniversityNumber()) != null) {
            System.out.println("this university already exist");
            AdminMenu.run();
        }
        if (ApplicationContext.getUniversityService().isValid(university)) {
            ApplicationContext.getUniversityService().save(university);
            AdminMenu.run();
        } else addUniversity();
    }
}
