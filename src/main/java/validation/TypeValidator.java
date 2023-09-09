package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static ui.UserMenu.scanner;

public class TypeValidator {
    public static long getLongInput() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("true")) {
                return true;
            } else if (input.equalsIgnoreCase("false")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
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
                System.out.println("Invalid date format. Please use yyyy,M,d.");
            }
        }
        return localDate;
    }
}

