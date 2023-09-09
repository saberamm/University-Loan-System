package validation;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL_CHARACTERS = "@#$%&";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARACTERS = CAPITAL_LETTERS + LOWERCASE_LETTERS + SPECIAL_CHARACTERS + DIGITS;

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));

        password.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));

        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        for (int i = 4; i < 8; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        return password.toString();
    }
}
