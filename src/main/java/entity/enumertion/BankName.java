package entity.enumertion;

import validation.TypeValidator;

public enum BankName {
    MASKAN,
    REFAH,
    MELLI,
    TEJARAT;

    public static BankName checkBank(String creditCardNumber) {
        if (creditCardNumber.startsWith("603799")) {
            return BankName.MELLI;
        } else if (creditCardNumber.startsWith("628023")) {
            return BankName.MASKAN;
        } else if (creditCardNumber.startsWith("627353")) {
            return BankName.TEJARAT;
        } else if (creditCardNumber.startsWith("589463")) {
            return BankName.REFAH;
        } else {
            return null;
        }
    }
}