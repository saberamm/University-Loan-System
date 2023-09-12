package ui;

import util.SecurityContext;
import validation.TypeValidator;


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
                UniversityMenu.addUniversity();
            case 0:
                SecurityContext.clear();
                UserMenu.run();
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                run();
        }
    }
}
