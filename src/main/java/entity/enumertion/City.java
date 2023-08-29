package entity.enumertion;

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
}