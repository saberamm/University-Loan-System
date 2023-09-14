package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static ui.UserMenu.scanner;

public class TypeValidator {
    public static long getLongInput() {
        while (true) {
            if (scanner.hasNextLong()) {
                return scanner.nextLong();
            } else {
                System.out.println("Invalid input. Please enter a valid long integer.");
                scanner.next();
            }
        }
    }

    public static int getIntInput() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    public static boolean getBooleanInput() {
        while (true) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("yes")) {
                return true;
            } else if (input.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    public static LocalDate dateFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,M,d");
        LocalDate localDate = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a date in the format (yyyy,M,d): ");
            String dateString = scanner.next();

            try {
                localDate = LocalDate.parse(dateString, formatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy,M,d");
            }
        }
        return localDate;
    }

    public static LocalDate cardDateFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,M,d");
        LocalDate localDate = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a date in the format (yy,M): ");
            String dateString = scanner.next();

            try {
                localDate = LocalDate.parse("13" + dateString + ",01", formatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy,M,d");
            }
        }
        return localDate;
    }

    public static String getDigitString() {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            input = scanner.nextLine();

            if (input.matches("\\d+")) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please enter only digits number");
            }
        }

        return input;
    }
    public static String getDigitString(int desiredLength) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            input = scanner.nextLine();

            if (input.matches("\\d+") && input.length() == desiredLength) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please enter a " + desiredLength + "-digit number.");
            }
        }

        return input;
    }
}


