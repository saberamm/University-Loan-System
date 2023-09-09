package ui;

import entity.Loan;
import entity.Student;
import entity.enumertion.LoanType;
import util.ApplicationContext;
import util.SecurityContext;
import validation.TypeValidator;

import static ui.UserMenu.scanner;

public class StudentMenu {
    public static void run() {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. get Loan Section");
        System.out.println("2. pay Installment Section");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                addLoan();
            case 2:
                payInstallment();
            case 0:
                SecurityContext.clear();
                UserMenu.run();
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                run();
        }
    }

    private static void payInstallment() {
    }

    private static void addLoan() {
        Student student = ApplicationContext.getStudentService().findByStudentNumber(SecurityContext.studentNumber);
        Loan loan = new Loan();
        System.out.print("Enter the type of loan you want :");
        loan.setLoanType(LoanType.selectLoan());
        if (student.getSpouseNationalCode() == null & loan.getLoanType().equals(LoanType.HOUSE_LOAN)) {
            System.out.print("Enter your Spouse national code :");
            student.setSpouseNationalCode(scanner.next());
            ApplicationContext.getStudentService().update(student);
        }

    }
}
