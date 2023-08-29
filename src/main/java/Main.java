import entity.User;
import util.ApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User user = new User("sss", "sss", "sss", "ssssssss", LocalDate.of(1999, 9, 9));
        System.out.println(ApplicationContext.getUserService().save(user));
    }
}
