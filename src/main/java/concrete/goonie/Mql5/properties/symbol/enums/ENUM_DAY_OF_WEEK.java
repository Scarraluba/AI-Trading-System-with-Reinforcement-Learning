package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_DAY_OF_WEEK {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String dayName;

    ENUM_DAY_OF_WEEK(String dayName) {
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }

    @Override
    public String toString() {
        return dayName;
    }
}
