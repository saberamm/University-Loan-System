package entity.enumertion;

import validation.TypeValidator;

public enum UniversityType {
    DOLATI,
    GHEYER_ENTEFAHI,
    PARDIS,
    ZARFIAT_MAZAD,
    PAYAME_NOOR,
    ELMI_KARBORDI,
    AZAD;

    public static UniversityType selectUniversityType() {
        System.out.println("Please select a university type:");
        System.out.println("0 - DOLATI");
        System.out.println("1 - GHEYER_ENTEFAHI");
        System.out.println("2 - PARDIS");
        System.out.println("3 - ZARFIAT_MAZAD");
        System.out.println("4 - PAYAME_NOOR");
        System.out.println("5 - ELMI_KARBORDI");
        System.out.println("6 - AZAD");
        int userInput = TypeValidator.getIntInput();
        switch (userInput) {
            case 0:
                return UniversityType.DOLATI;
            case 1:
                return UniversityType.GHEYER_ENTEFAHI;
            case 2:
                return UniversityType.PARDIS;
            case 3:
                return UniversityType.ZARFIAT_MAZAD;
            case 4:
                return UniversityType.PAYAME_NOOR;
            case 5:
                return UniversityType.ELMI_KARBORDI;
            case 6:
                return UniversityType.AZAD;
            default:
                System.out.println("invalid choice please try again");
                return selectUniversityType();
        }
    }
}