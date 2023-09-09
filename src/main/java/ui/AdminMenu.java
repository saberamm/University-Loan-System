package ui;

import entity.University;
import entity.enumertion.City;
import entity.enumertion.UniversityType;
import util.ApplicationContext;
import util.SecurityContext;
import validation.TypeValidator;

import static ui.UserMenu.scanner;

public class AdminMenu {
    public static void run() {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. Add University Section");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                addUniversity();
            case 0:
                SecurityContext.clear();
                UserMenu.run();
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                run();
        }
    }

    private static void addUniversity() {
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
            run();
        }
        if (ApplicationContext.getUniversityService().isValid(university)) {
            ApplicationContext.getUniversityService().save(university);
            run();
        } else addUniversity();
    }
}
