package ui;

import util.SecurityContext;
import util.SystemTime;
import validation.TypeValidator;

public class StudentMenu {
    public static void run() {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. get Loan Section");
        System.out.println("2. Installment Section");
        System.out.println("3. Time machine");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                SystemTime.isLoanTime();
            case 2:
                InstallmentMenu.paySection();
            case 3:
                SystemTime.timeSetter();
            case 0:
                SecurityContext.clear();
                UserMenu.run();
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                run();
        }
    }
}
