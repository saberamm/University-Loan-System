package entity.enumertion;

import validation.TypeValidator;

public enum LoanType {
    TUITION_LOAN,
    EDUCATION_LOAN,
    HOUSE_LOAN;
    public static LoanType selectLoan() {
        System.out.println("Please select a loan:");
        System.out.println("0 - TUITION_LOAN");
        System.out.println("1 - EDUCATION_LOAN");
        System.out.println("2 - HOUSE_LOAN");
        int userInput = TypeValidator.getIntInput();
        switch (userInput) {
            case 0:
                return LoanType.TUITION_LOAN;
            case 1:
                return LoanType.EDUCATION_LOAN;
            case 2:
                return LoanType.HOUSE_LOAN;
            default:
                System.out.println("invalid choice please try again");
                return selectLoan();
        }
    }
}
