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
        university.setUniversityNumber(TypeValidator.getDigitString(5));
        System.out.print("Enter the university name :");
        university.setUniversityName(scanner.next());
        scanner.nextLine();
        System.out.print("Enter the university type :");
        university.setUniversityType(UniversityType.selectUniversityType());
        if (university.getUniversityType().equals(UniversityType.DOLATI)) {
            System.out.print("is this university daily ? (yes or no to answer) :");
            university.setDaily(TypeValidator.getBooleanInput());
        }
        System.out.print("Enter the university city :");
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
