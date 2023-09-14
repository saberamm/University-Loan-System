package ui;

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

import static ui.CreditCardMenu.addCreditCard;
import static ui.HouseInfoMenu.addHouse;

public class LoanMenu {
    public static void addLoan() {
        Student student = ApplicationContext.getStudentService().findByStudentNumber(SecurityContext.studentNumber);
        if (isGraduate(student)) {
            System.out.println("you cant get a new loan because you got graduated");
            StudentMenu.run();
        }
        Loan loan = new Loan();
        loan.setSemester(yearSemester(SystemTime.systemTime));
        loan.setLoanType(LoanType.selectLoan());
        loan.setGrade(student.getGrade());
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && hasHouseLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), student)) {
            System.out.println("you cant get another house loan because you already get one in this grade");
            StudentMenu.run();
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && student.getSpouseNationalCode() == null) {
            System.out.print("Enter your Spouse national code :");
            student.setSpouseNationalCode(TypeValidator.getDigitString(10));
            ApplicationContext.getStudentService().update(student);
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) && hasSpouseHouseLoan(ApplicationContext.getLoanService().getLoansByNationalCode(student.getSpouseNationalCode()))) {
            System.out.println("you cant get house loan because your spouse already get one");
            StudentMenu.run();
        }
        if (student.getDormitoryResident() && loan.getLoanType() == LoanType.HOUSE_LOAN) {
            System.out.println("the dormitory residents cant get a house loan");
            StudentMenu.run();
        }
        if (loan.getLoanType().equals(LoanType.HOUSE_LOAN) & student.getHouseInfo() == null) {
            addHouse(student);
        }
        if (student.getUniversity().getUniversityType().equals(UniversityType.DOLATI) && loan.getLoanType().equals(LoanType.TUITION_LOAN)) {
            System.out.println("tuition loan doesn't belong to student with dolati university");
            StudentMenu.run();
        }
        if (loan.getLoanType().equals(LoanType.TUITION_LOAN) && hasTuitionLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), loan.getSemester())) {
            System.out.println("you cant get another tuition loan because you already get one in this semester");
            StudentMenu.run();
        }
        if (loan.getLoanType().equals(LoanType.EDUCATION_LOAN) && hasEducationLoan(ApplicationContext.getLoanService().getLoansByStudentNumber(student.getStudentNumber()), loan.getSemester())) {
            System.out.println("you cant get another education loan because you already get one in this semester");
            StudentMenu.run();
        }
        loan.setLoanNumber(appendRandomDigits(student.getNationalCode(), 5));
        loan.setLoanAmount(loanAmount(student, loan));
        loan.setCreditCard(addCreditCard());
        loan.setStudent(student);
        if (ApplicationContext.getLoanService().isValid(loan)) {
            ApplicationContext.getLoanService().save(loan);
            InstallmentMenu.addInstallment(loan);
            System.out.println("loan payed successfully");
            StudentMenu.run();
        } else StudentMenu.run();
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
            StudentMenu.run();
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
        } else {
            return SystemTime.systemTime.isAfter(student.getEnterYear().plusYears(5));
        }
    }

}
