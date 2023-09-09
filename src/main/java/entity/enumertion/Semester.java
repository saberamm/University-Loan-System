package entity.enumertion;


import validation.TypeValidator;

public enum Semester {
    term_1_1400,
    term_2_1400,
    term_1_1401,
    term_2_1401,
    term_1_1402,
    term_2_1402,
    term_1_1403,
    term_2_1403,
    term_1_1404,
    term_2_1404,
    term_1_1405,
    term_2_1405,
    term_1_1406,
    term_2_1406,
    term_1_1407,
    term_2_1407,
    term_1_1408,
    term_2_1408,
    term_1_1409,
    term_2_1409,
    term_1_1410,
    term_2_1410,
    term_1_1411,
    term_2_1411;

    public static Semester selectSemester() {
        Semester selectedSemester = null;

        System.out.println("Available Semesters:");
        int index = 1;
        for (Semester semester : Semester.values()) {
            System.out.println(index + ". " + semester.name().replace("_", " "));
            index++;
        }

        while (selectedSemester == null) {
            System.out.print("Enter the number of the desired semester: ");
            int choice = TypeValidator.getIntInput();

            if (choice >= 1 && choice <= Semester.values().length) {
                selectedSemester = Semester.values()[choice - 1];
            } else {
                System.out.println("Invalid choice. Please choose a valid semester.");
            }
        }
        return selectedSemester;
    }
}
