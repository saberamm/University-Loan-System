package util;

import java.time.LocalDate;

public class SystemTime {
    public static LocalDate systemTime;

    public static LocalDate getSystemTime() {
        return systemTime;
    }

    public static void setSystemTime(LocalDate systemTime) {
        SystemTime.systemTime = systemTime;
    }
}
