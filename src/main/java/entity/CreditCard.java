package entity;

import entity.enumertion.BankName;

import java.time.LocalDate;

public class CreditCard {
    private BankName bankName;
    private String NationalCode;
    private String creditCardNumber;
    private String cvv2;
    private LocalDate expireDate;
    private Long cardAmount;
}
