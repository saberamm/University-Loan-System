package entity.enumertion;

import static ui.UserMenu.scanner;

public enum City {
    ALBORZ,
    ARDABIL,
    BUSHEHR,
    CHAHARMAHAL_AND_BAKHTIARI,
    EAST_AZERBAIJAN,
    FARS,
    GILAN,
    GOLESTAN,
    HAMADAN,
    HORMOZGAN,
    ILAM,
    ISFAHAN,
    KERMAN,
    KERMANSHAH,
    KHORASAN_RAZAVI,
    KHUZESTAN,
    KOHGILUYEH_AND_BOYER_AHMAD,
    KURDISTAN,
    LORESTAN,
    MAZANDARAN,
    MARKAZI,
    NORTH_KHORASAN,
    QAZVIN,
    QOM,
    SEMNAN,
    SISTAN_AND_BALUCHESTAN,
    SOUTH_KHORASAN,
    TEHRAN,
    WEST_AZERBAIJAN,
    YAZD,
    ZANJAN;

    public static boolean majorCity(City city) {
        return switch (city) {
            case GILAN, ALBORZ, EAST_AZERBAIJAN, ISFAHAN, FARS, KHORASAN_RAZAVI, KHUZESTAN, QOM -> true;
            default -> false;
        };
    }

    public static boolean capitalCity(City city) {
        return city == TEHRAN;
    }
    public static City selectCity() {

        for (int i = 0; i < City.values().length; i++) {
            System.out.println(i + " - " + City.values()[i]);
        }

        System.out.print("Please select a city:");

        int userInput = -1;
        do {
            try {
                userInput = Integer.parseInt(scanner.nextLine());

                if (userInput < 0 || userInput >= City.values().length) {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (userInput < 0 || userInput >= City.values().length);

        return City.values()[userInput];
    }

    public static void main(String[] args) {
        City selectedCity = selectCity();
        System.out.println("Selected City: " + selectedCity);
    }
}