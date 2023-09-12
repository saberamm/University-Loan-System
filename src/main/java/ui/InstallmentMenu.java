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
import static ui.UserMenu.scanner;

public class InstallmentMenu {
    public static void paySection() {
        Student student = ApplicationContext.getStudentService().findByStudentNumber(SecurityContext.studentNumber);
        if (!isGraduate(student)) {
            System.out.println("you cant pay any installment because you not graduated");
            StudentMenu.run();
        }
        List<Loan> loanList = student.getLoans();
        for (int i = 0; i <= loanList.size() - 1; i++) {
            System.out.println(loanList.get(i));
        }
        System.out.print("Enter the loan number you want to pay installments");
        String loanNumber = scanner.next();
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

        installmentCheck(loan);
    }

    public static void installmentCheck(Loan loan) {
        for (int i = 0; i <= Objects.requireNonNull(loan).getInstallments().size() - 1; i++) {
            System.out.println(loan.getInstallments().get(i));
        }
        System.out.println("Enter the installment number you want to pay");
        String installmentNumber = scanner.next();
        if (!isInstallmentExist(loan, installmentNumber)) {
            System.out.println("installment number doesn't exist try again");
            installmentCheck(loan);
        }
        payInstallment(loan, installmentNumber);
    }

    public static void payInstallment(Loan loan, String installmentNumber) {
        Installment installment = ApplicationContext.getInstallmentService().findByInstallmentNumber(installmentNumber);
        CreditCard loanCreditCard = loan.getCreditCard();
        CreditCard clientCreditCard = new CreditCard();
        System.out.print("Enter the credit card number :");
        clientCreditCard.setCreditCardNumber(scanner.next());
        if (!loanCreditCard.getCreditCardNumber().equals(clientCreditCard.getCreditCardNumber())) {
            System.out.println("credit card number is not correct try again");
            payInstallment(loan, installmentNumber);
        }
        System.out.print("Enter cvv2 :");
        clientCreditCard.setCvv2(scanner.next());
        if (!loanCreditCard.getCvv2().equals(clientCreditCard.getCvv2())) {
            System.out.println("cvv2 is not correct try again");
            payInstallment(loan, installmentNumber);
        }
        System.out.print("Enter expire date :");
        clientCreditCard.setExpire(TypeValidator.dateFormatter());
        if (!loanCreditCard.getExpire().equals(clientCreditCard.getExpire())) {
            System.out.println("expire date is not correct try again");
            payInstallment(loan, installmentNumber);
        }
        installment.setIsPayed(true);
        ApplicationContext.getInstallmentService().update(installment);
        StudentMenu.run();
    }

    public static boolean isInstallmentExist(Loan loan, String installmentPay) {
        for (int i = 0; i <= Objects.requireNonNull(loan).getInstallments().size() - 1; i++) {
            if (loan.getInstallments().get(i).getInstallmentNumber().equals(installmentPay)) {
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