package ui;

import entity.CreditCard;
import entity.HouseInfo;
import entity.Loan;
import entity.Student;
import entity.enumertion.*;
import util.ApplicationContext;
import util.SecurityContext;
import util.SystemTime;
import validation.TypeValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static ui.UserMenu.scanner;

public class StudentMenu {
    public static void run() {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. get Loan Section");
        System.out.println("2. pay Installment Section");
        System.out.println("3. Time machine");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                SystemTime.isLoanTime();
            case 2:
                payInstallment();
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

    private static void payInstallment() {
    }


    public static void addLoan() {
        Student student = ApplicationContext.getStudentService().findByStudentNumber(SecurityContext.studentNumber);
        if (isGraduate(student)) {
            System.out.println("you cant get a new loan because you got graduated");
            run();
        }
        Loan loan = new Loan();
        System.out.print("Enter the type of loan you want :");
        loan.setLoanType(LoanType.selectLoan());
        loan.setGrade(student.getGrade());
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && hasHouseLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), student)) {
            System.out.println("you cant get another house loan because you already get one in this grade");
            run();
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && student.getSpouseNationalCode() == null) {
            System.out.print("Enter your Spouse national code :");
            student.setSpouseNationalCode(scanner.next());
            ApplicationContext.getStudentService().update(student);
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && hasSpouseHouseLoan(ApplicationContext.getLoanService().getLoansByNationalCode(student.getSpouseNationalCode()))) {
            System.out.println("you cant get house loan because your spouse already get one");
            run();
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN)) {
            addHouse(student);
        }
        loan.setSemester(yearSemester(SystemTime.systemTime));
        if (student.getUniversity().getUniversityType().equals(UniversityType.DOLATI) && loan.getLoanType().equals(LoanType.TUITION_LOAN)) {
            System.out.println("tuition loan doesn't belong to student with dolati university");
            run();
        }
        if (hasTuitionLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), loan.getSemester())) {
            System.out.println("you cant get another tuition loan because you already get one in this semester");
            run();
        }
        if (hasEducationLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), loan.getSemester())) {
            System.out.println("you cant get another education loan because you already get one in this semester");
            run();
        }
        loan.setLoanNumber(appendRandomDigits(student.getNationalCode(), 5));
        loan.setLoanAmount(loanAmount(student, loan));
    }

    public static void addHouse(Student student) {
        HouseInfo houseInfo = new HouseInfo();
        System.out.print("Enter the house rent number :");
        houseInfo.setRentNumber(scanner.next());
        System.out.print("Enter your house address");
        houseInfo.setHouseAddress(scanner.next());
        student.setHouseInfo(houseInfo);
    }

    public static void addCreditCard(Student student) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(scanner.next());
        creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
        if (creditCard.getBankName() == null) {
            System.out.println("credit card number is not valid");
            addCreditCard(student);
        }
        System.out.print("Enter expire date :");
        creditCard.setExpire(TypeValidator.dateFormatter());
        System.out.print("Enter cvv2 :");
        creditCard.setCvv2(scanner.next());
    }


    public static Long loanAmount(Student student, Loan loan) {
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN)) {
            if (City.majorCity(student.getUniversity().getCity())) {
                return 26000000L;
            }
            if (City.capitalCity(student.getUniversity().getCity())) {
                return 32000000L;
            } else return 19500000L;
        }
        if (loan.getLoanType().equals(LoanType.EDUCATION_LOAN)) {
            if (student.getGrade().equals(Grade.ASSOCIATE)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_BACHELOR)
                    || student.getGrade().equals(Grade.CONTINUOUS_BACHELOR)) {
                return 1900000L;
            }
            if (student.getGrade().equals(Grade.CONTINUOUS_MASTER)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_MASTER)
                    || student.getGrade().equals(Grade.CONTINUOUS_PROFESSIONAL_DOCTORAL)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_PROFESSIONAL_DOCTORAL)) {
                return 2250000L;
            }
            if (student.getGrade().equals(Grade.EXPERT_DOCTORAL)) {
                return 2600000L;
            }
        }
        if (loan.getLoanType().equals(LoanType.TUITION_LOAN)) {
            if (student.getGrade().equals(Grade.ASSOCIATE)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_BACHELOR)
                    || student.getGrade().equals(Grade.CONTINUOUS_BACHELOR)) {
                return 1300000L;
            }
            if (student.getGrade().equals(Grade.CONTINUOUS_MASTER)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_MASTER)
                    || student.getGrade().equals(Grade.CONTINUOUS_PROFESSIONAL_DOCTORAL)
                    || student.getGrade().equals(Grade.DISCONTINUOUS_PROFESSIONAL_DOCTORAL)) {
                return 2600000L;
            }
            if (student.getGrade().equals(Grade.EXPERT_DOCTORAL)) {
                return 6500000L;
            }
        }
        return null;
    }


    public static String appendRandomDigits(String input, int numberOfDigits) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(input);

        for (int i = 0; i < numberOfDigits; i++) {
            int randomDigit = random.nextInt(10);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }


    public static boolean hasSpouseHouseLoan(List<Loan> loans) {
        for (Loan loan : loans) {
            if (loan.getLoanType() == LoanType.HOUSE_LOAN) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasHouseLoan(List<Loan> loans, Student student) {
        for (Loan loan : loans) {
            if (loan.getLoanType() == LoanType.HOUSE_LOAN && loan.getGrade().equals(student.getGrade())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTuitionLoan(List<Loan> loans, Semester semester) {
        for (Loan loan : loans) {
            if (loan.getLoanType() == LoanType.TUITION_LOAN && loan.getSemester() == semester) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasEducationLoan(List<Loan> loans, Semester semester) {
        for (Loan loan : loans) {
            if (loan.getLoanType() == LoanType.EDUCATION_LOAN && loan.getSemester() == semester) {
                return true;
            }
        }
        return false;
    }

    public static Semester yearSemester(LocalDate localDate) {
        if (localDate.isBefore(LocalDate.of(1400, 1, 1)) ||
                localDate.isAfter(LocalDate.of(1411, 10, 30))) {
            System.out.println("there is no semester for this time");
            return null;
        }
        String s;
        if (localDate.isAfter(LocalDate.of(localDate.getYear(), 11, 1))) {
            s = "term_1_" + (localDate.getYear() + 1);
        } else {
            s = "term_2_" + localDate.getYear();
        }
        return Semester.valueOf(s);
    }

    public static boolean isGraduate(Student student) {
        if (student.getGrade().equals(Grade.CONTINUOUS_BACHELOR)) {
            return SystemTime.systemTime.isAfter(student.getEnterYear().plusYears(4));
        }
        if (student.getGrade().equals(Grade.ASSOCIATE)
                || student.getGrade().equals(Grade.DISCONTINUOUS_MASTER)
                || student.getGrade().equals(Grade.DISCONTINUOUS_BACHELOR)) {
            return SystemTime.systemTime.isAfter(student.getEnterYear().plusYears(2));
        }
        if (student.getGrade().equals(Grade.CONTINUOUS_MASTER)) {
            return SystemTime.systemTime.isAfter(student.getEnterYear().plusYears(6));
        }
        if (student.getGrade().equals(Grade.CONTINUOUS_PROFESSIONAL_DOCTORAL)
                || student.getGrade().equals(Grade.DISCONTINUOUS_PROFESSIONAL_DOCTORAL)
                || student.getGrade().equals(Grade.EXPERT_DOCTORAL)) {
            return SystemTime.systemTime.isAfter(student.getEnterYear().plusYears(5));
        }
        return false;
    }

}
