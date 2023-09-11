package util;

import ui.StudentMenu;
import validation.TypeValidator;

import java.time.LocalDate;

public class SystemTime {
    public static LocalDate systemTime;

    public static void isLoanTime() {
        LocalDate currentDate = systemTime;

        LocalDate firstWindowStart = LocalDate.of(currentDate.getYear(), 8, 1);
        LocalDate firstWindowEnd = firstWindowStart.plusWeeks(1);

        LocalDate secondWindowStart = LocalDate.of(currentDate.getYear(), 11, 25);
        LocalDate secondWindowEnd = secondWindowStart.plusWeeks(1);

        if ((currentDate.isEqual(firstWindowStart) || currentDate.isAfter(firstWindowStart)) && currentDate.isBefore(firstWindowEnd)) {
            StudentMenu.addLoan();
        } else if ((currentDate.isEqual(secondWindowStart) || currentDate.isAfter(secondWindowStart)) && currentDate.isBefore(secondWindowEnd)) {
            StudentMenu.addLoan();
        } else {
            System.out.println("At this time you cant get a new loan come back later");
            StudentMenu.run();
        }

    }

    public static void timeSetter() {
        System.out.print("Enter the current date :");
        SystemTime.systemTime = TypeValidator.dateFormatter();
        StudentMenu.run();
    }
}
