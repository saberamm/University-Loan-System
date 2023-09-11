package ui;

import entity.CreditCard;
import entity.enumertion.BankName;
import validation.TypeValidator;

import static ui.UserMenu.scanner;

public class CreditCardMenu {
    public static CreditCard addCreditCard() {
        CreditCard creditCard = new CreditCard();
        System.out.print("Enter 16 digit credit card number :");
        creditCard.setCreditCardNumber(scanner.next());
        creditCard.setBankName(BankName.checkBank(creditCard.getCreditCardNumber()));
        if (creditCard.getBankName() == null) {
            System.out.println("credit card number is not valid");
            addCreditCard();
        }
        System.out.println("Enter expire date :");
        creditCard.setExpire(TypeValidator.dateFormatter());
        System.out.print("Enter cvv2 :");
        creditCard.setCvv2(scanner.next());
        return creditCard;
    }
}
