package concrete.goonie.Mql5.utils;


import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePeriodUtil {
    public static boolean isNextTimeMatched(ENUM_TIMEFRAME timePeriod) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String currentTimeStr = sdf.format(currentTime);
        String[] timeParts = currentTimeStr.split(":");

        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        switch (timePeriod) {
            case PERIOD_M1:
                return seconds == 0;
            case PERIOD_M2:
                return minutes % 2 == 0 && seconds == 0;
            case PERIOD_M3:
                return minutes % 3 == 0 && seconds == 0;
            case PERIOD_M4:
                return minutes % 4 == 0 && seconds == 0;
            case PERIOD_M5:
                return minutes % 5 == 0 && seconds == 0;
            case PERIOD_M6:
                return minutes % 6 == 0 && seconds == 0;
            case PERIOD_M10:
                return minutes % 10 == 0 && seconds == 0;
            case PERIOD_M12:
                return minutes % 12 == 0 && seconds == 0;
            case PERIOD_M15:
                return minutes % 15 == 0 && seconds == 0;
            case PERIOD_M20:
                return minutes % 20 == 0 && seconds == 0;
            case PERIOD_M30:
                return minutes % 30 == 0 && seconds == 0;
            case PERIOD_H1:
                return minutes == 0 && seconds == 0;
            case PERIOD_H2:
                return hours % 2 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_H3:
                return hours % 3 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_H4:
                return hours % 4 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_H6:
                return hours % 6 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_H8:
                return hours % 8 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_H12:
                return hours % 12 == 0 && minutes == 0 && seconds == 0;
            case PERIOD_D1:
                return hours == 0 && minutes == 0 && seconds == 0;
            case PERIOD_W1:
                return dayOfWeek == Calendar.MONDAY && hours == 0 && minutes == 0 && seconds == 0;
            case PERIOD_MN1:
                return dayOfMonth == 1 && hours == 0 && minutes == 0 && seconds == 0;
        }
        return false;
    }


    public static void main() {
        ENUM_TIMEFRAME chosenTimePeriod = ENUM_TIMEFRAME.PERIOD_M1;
        boolean isMatched = isNextTimeMatched(chosenTimePeriod);
        System.out.println("Is the current time matched for " + chosenTimePeriod.getDescription() + "? " + isMatched);
    }
}
