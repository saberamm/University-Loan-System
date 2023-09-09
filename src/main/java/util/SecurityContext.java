package util;

public class SecurityContext {
    public static Long id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String studentNumber;

    public static void clear() {
        id = null;
        firstname = null;
        lastname = null;
        username = null;
        studentNumber = null;
    }
}
