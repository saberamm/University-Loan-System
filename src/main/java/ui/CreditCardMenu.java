package ui;

import entity.CreditCard;
import entity.enumertion.BankName;
import util.ApplicationContext;
import validation.TypeValidator;


public class CreditCardMenu {
    public static CreditCard addCreditCard() {
        CreditCard creditCard = new CreditCard();
        System.out.print("Enter 16 digit credit card number :");
        creditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
        creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
        while (creditCard.getBankName() == null) {
            System.out.println("credit card number is not valid");
            System.out.print("Enter 16 digit credit card number :");
            creditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
            creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
        }
        System.out.print("Enter expire date :");
        creditCard.setExpire(TypeValidator.cardDateFormatter());
        System.out.print("Enter cvv2 :");
        creditCard.setCvv2(TypeValidator.getDigitString(4));
        while (!ApplicationContext.getCreditCardService().isValid(creditCard)) {
            System.out.print("Enter 16 digit credit card number :");
            creditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
            creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
            while (creditCard.getBankName() == null) {
                System.out.println("credit card number is not valid");
                System.out.print("Enter 16 digit credit card number :");
                creditCard.setCreditCardNumber(TypeValidator.getDigitString(16));
                creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
            }
            System.out.print("Enter expire date :");
            creditCard.setExpire(TypeValidator.cardDateFormatter());
            System.out.print("Enter cvv2 :");
            creditCard.setCvv2(TypeValidator.getDigitString(4));
        }
        return creditCard;
    }
}
