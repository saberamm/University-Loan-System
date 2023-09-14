package ui;

import entity.CreditCard;
import entity.Installment;
import entity.Loan;
import entity.Student;
import entity.enumertion.Grade;
import util.ApplicationContext;
import util.SecurityContext;
import validation.TypeValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ui.LoanMenu.appendRandomDigits;
import static ui.LoanMenu.isGraduate;

public class InstallmentMenu {
    public static void paySection() {
        Student student = ApplicationContext.getStudentService().findByStudentNumber(SecurityContext.studentNumber);
        if (!isGraduate(student)) {
            System.out.println("you cant pay any installment because you not graduated");
            StudentMenu.run();
        }
        List<Loan> loanList = ApplicationContext.getLoanService().getLoansByNationalCode(student.getNationalCode());
        if (loanList.size() == 0) {
            System.out.println("you dont have any loan");
            StudentMenu.run();
        }
        for (int i = 0; i <= Objects.requireNonNull(loanList).size() - 1; i++) {
            System.out.println(loanList.get(i));
        }
        System.out.print("Enter a loan number :");
        String loanNumber = TypeValidator.getDigitString(15);
        Loan loan = null;
        for (int i = 0; i <= loanList.size() - 1; i++) {
            if (loanList.get(i).getLoanNumber().equals(loanNumber)) {
                loan = loanList.get(i);
                break;
            }
        }
        if (loan == null) {
            System.out.println("there is no loan with this number");
            StudentMenu.run();
        }

        assert loan != null;
        ChooseInstallmentMenu(loan.getId());
    }

    public static void ChooseInstallmentMenu(Long loanId) {
        int choice;
        System.out.println("================");
        System.out.println("Options:");
        System.out.println("1. pay Installments");
        System.out.println("2. see payed Installment");
        System.out.println("3. see unpayed installment");
        System.out.println("0. exit");
        System.out.print("Enter your choice: ");
        choice = TypeValidator.getIntInput();
        System.out.println();

        switch (choice) {
            case 1:
                installmentCheck(loanId);
            case 2:
                payedInstallments(loanId);
            case 3:
                unpayedInstallments(loanId);
            case 0:
                StudentMenu.run();
            default:
                System.out.println("*****Invalid choice. Please try again*****");
                ChooseInstallmentMenu(loanId);
        }
    }

    private static void unpayedInstallments(Long loanId) {
        List<Installment> installments = ApplicationContext.getInstallmentService().findByInstallmentsLoanId(loanId);
        for (int i = 0; i <= installments.size() - 1; i++) {
            if (installments.get(i).getIsPayed().equals(false)) {
                System.out.println(installments.get(i));
            }
        }
        ChooseInstallmentMenu(loanId);
    }

    private static void payedInstallments(Long loanId) {
        List<Installment> installments = ApplicationContext.getInstallmentService().findByInstallmentsLoanId(loanId);
        for (int i = 0; i <= installments.size() - 1; i++) {
            if (installments.get(i).getIsPayed().equals(true)) {
                System.out.println(installments.get(i));
            }
        }
        ChooseInstallmentMenu(loanId);
    }

    public static void installmentCheck(Long loanId) {
        List<Installment> installments = ApplicationContext.getInstallmentService().findByInstallmentsLoanId(loanId);
        for (int i = 0; i <= installments.size() - 1; i++) {
            System.out.println(installments.get(i));
        }
        System.out.print("Enter the installment number you want to pay :");
        String installmentNumber = TypeValidator.getDigitString(20);
        while (!isInstallmentExist(installments, installmentNumber)) {
            System.out.println("installment number doesn't exist try again");
            System.out.print("Enter the installment number you want to pay :");
            installmentNumber = TypeValidator.getDigitString(20);
        }
        payInstallment(installmentNumber);
    }

    public static void payInstallment(String installmentNumber) {
        Installment installment = ApplicationContext.getInstallmentService().findByInstallmentNumber(installmentNumber);
        Loan loan = ApplicationContext.getLoanService().findById(installment.getLoan().getId());
        if (installment.getIsPayed()) {
            System.out.println("this installment is already payed");
            StudentMenu.run();
        }
        CreditCard loanCreditCard = loan.getCreditCard();
        CreditCard clientCreditCard = new CreditCard();

        System.out.print("Enter the credit card number :");
        clientCreditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
        while (!loanCreditCard.getCreditCardNumber().equals(clientCreditCard.getCreditCardNumber())) {
            System.out.print("credit card number is not correct try again :");
            clientCreditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
        }
        System.out.print("Enter cvv2 :");
        clientCreditCard.setCvv2(TypeValidator.getDigitString(4));
        while (!loanCreditCard.getCvv2().equals(clientCreditCard.getCvv2())) {
            System.out.print("cvv2 is not correct try again :");
            clientCreditCard.setCvv2(TypeValidator.getDigitString(4));
        }
        System.out.print("Enter expire date :");
        clientCreditCard.setExpire(TypeValidator.cardDateFormatter());
        while (!loanCreditCard.getExpire().equals(clientCreditCard.getExpire())) {
            System.out.print("expire date is not correct try again :");
            clientCreditCard.setExpire(TypeValidator.cardDateFormatter());
        }
        installment.setIsPayed(true);
        ApplicationContext.getInstallmentService().update(installment);
        StudentMenu.run();
    }

    public static boolean isInstallmentExist(List<Installment> installments, String installmentPay) {
        for (int i = 0; i <= installments.size() - 1; i++) {
            if (installments.get(i).getInstallmentNumber().equals(installmentPay)) {
                return true;
            }
        }
        return false;
    }

    public static void addInstallment(Loan loan) {
        List<Installment> installmentList = new ArrayList<>();
        double amount = Double.valueOf(loan.getLoanAmount());
        amount += amount * 0.04;
        double pointAmount = amount / 31;
        BigDecimal bd = new BigDecimal(pointAmount).setScale(1, RoundingMode.HALF_UP);
        pointAmount = bd.doubleValue();
        int pointCounter = 1;
        LocalDate payTimeCounter = graduateTime(loan);
        for (int i = 1; i <= 5; i++) {
            Installment installment = new Installment();
            installment.setInstallmentAmount(pointAmount * pointCounter);
            installment.setInstallmentNumber(appendRandomDigits(loan.getLoanNumber(), 5));
            installment.setIsPayed(false);
            installment.setLoan(loan);
            installment.setPayTime(payTimeCounter.plusYears(i));
            installmentList.add(installment);
            pointCounter *= 2;
        }
        for (int i = 0; i <= installmentList.size() - 1; i++) {
            ApplicationContext.getInstallmentService().save(installmentList.get(i));
        }
    }


    public static LocalDate graduateTime(Loan loan) {
        if (loan.getStudent().getGrade().equals(Grade.CONTINUOUS_BACHELOR)) {
            return loan.getStudent().getEnterYear().plusYears(4);
        }
        if (loan.getStudent().getGrade().equals(Grade.ASSOCIATE)
                || loan.getStudent().getGrade().equals(Grade.DISCONTINUOUS_MASTER)
                || loan.getStudent().getGrade().equals(Grade.DISCONTINUOUS_BACHELOR)) {
            return loan.getStudent().getEnterYear().plusYears(2);
        }
        if (loan.getStudent().getGrade().equals(Grade.CONTINUOUS_MASTER)) {
            return loan.getStudent().getEnterYear().plusYears(6);
        } else {
            return loan.getStudent().getEnterYear().plusYears(5);
        }
    }
}