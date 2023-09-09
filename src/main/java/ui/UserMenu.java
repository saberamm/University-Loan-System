package ui;

import entity.Student;
import entity.User;
import entity.enumertion.Grade;
import util.ApplicationContext;
import util.SecurityContext;
import validation.PasswordGenerator;
import validation.TypeValidator;

import java.util.Scanner;

public class UserMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. Signing section");
        System.out.println("2. Signup section");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                userSigning();
            case 2:
                userSignup();
            case 0:
                System.exit(0);
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                run();
        }
    }

    private static void userSignup() {
        Student student = new Student();
        System.out.print("Enter your first name :");
        student.setFirstName(scanner.next());
        System.out.print("Enter your last name :");
        student.setLastName(scanner.next());
        System.out.print("Enter your father name :");
        student.setFatherName(scanner.next());
        System.out.print("Enter your mother name :");
        student.setMotherName(scanner.next());
        System.out.print("Enter the birth date :");
        student.setBirthDate(TypeValidator.dateFormatter());
        System.out.print("Enter the student number :");
        student.setStudentNumber(scanner.next());
        System.out.print("Enter your birthCertificateNumber :");
        student.setBirthCertificateNumber(scanner.next());
        System.out.print("Enter your nationalCode :");
        student.setNationalCode(scanner.next());
        System.out.print("Enter your university enter year :");
        student.setEnterYear(TypeValidator.dateFormatter());
        System.out.print("Enter your grade :");
        student.setGrade(Grade.selectGrade());
        System.out.print("are you a dormitory Resident ? (true or false to answer)");
        student.setDormitoryResident(TypeValidator.getBooleanInput());
        System.out.print("Enter your university number :");
        String universityNumber=scanner.next();
        student.setUniversity(ApplicationContext.getUniversityService().findByUniversityNumber(universityNumber));
        student.setUsername(student.getNationalCode());
        student.setPassword(PasswordGenerator.generatePassword());
        if (ApplicationContext.getStudentService().findByStudentNumber(student.getStudentNumber()) != null) {
            System.out.println("you already signed up");
            run();
        }
        if (ApplicationContext.getStudentService().isValid(student)) {
            ApplicationContext.getStudentService().save(student);
            run();
        } else userSignup();
    }

    public static void userSigning() {
        System.out.print("Enter username : ");
        String username = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();

        User user = ApplicationContext.getUserService().userAuthentication(username, password);
        if (user != null) {
            SecurityContext.id = user.getId();
            SecurityContext.username = user.getUsername();
            SecurityContext.firstname = user.getFirstName();
            SecurityContext.lastname = user.getLastName();
            if (user.getClass().getSimpleName().equals("Student")) {
                Student student = ApplicationContext.getStudentService().findById(user.getId());
                SecurityContext.studentNumber = student.getStudentNumber();
                StudentMenu.run();
            }
            if (user.getUsername().equals("Admin")) {
                AdminMenu.run();
            }
        } else {
            System.out.println("*****Username or password is wrong*****");
            UserMenu.userSigning();
        }
    }
}