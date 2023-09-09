package entity.enumertion;

import validation.TypeValidator;

public enum Grade {
    ASSOCIATE,
    DISCONTINUOUS_BACHELOR,
    CONTINUOUS_BACHELOR,
    DISCONTINUOUS_MASTER,
    CONTINUOUS_MASTER,
    DISCONTINUOUS_PROFESSIONAL_DOCTORAL,
    CONTINUOUS_PROFESSIONAL_DOCTORAL,
    EXPERT_DOCTORAL;

    public static Grade selectGrade() {
        System.out.println("Please select a grade:");
        System.out.println("0 - Associate");
        System.out.println("1 - Discontinuous Bachelor");
        System.out.println("2 - Continuous Bachelor");
        System.out.println("3 - Discontinuous Master");
        System.out.println("4 - Continuous Master");
        System.out.println("5 - Discontinuous Professional Doctoral");
        System.out.println("6 - Continuous Professional Doctoral");
        System.out.println("7 - Expert Doctoral");
        int userInput = TypeValidator.getIntInput();
        switch (userInput) {
            case 0:
                return Grade.ASSOCIATE;
            case 1:
                return Grade.DISCONTINUOUS_BACHELOR;
            case 2:
                return Grade.CONTINUOUS_BACHELOR;
            case 3:
                return Grade.DISCONTINUOUS_MASTER;
            case 4:
                return Grade.CONTINUOUS_MASTER;
            case 5:
                return Grade.DISCONTINUOUS_PROFESSIONAL_DOCTORAL;
            case 6:
                return Grade.CONTINUOUS_PROFESSIONAL_DOCTORAL;
            case 7:
                return Grade.EXPERT_DOCTORAL;
            default:
                System.out.println("invalid choice please try again");
                return selectGrade();
        }
    }
}