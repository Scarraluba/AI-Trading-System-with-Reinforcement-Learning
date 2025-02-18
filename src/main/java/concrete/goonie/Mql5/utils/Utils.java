package concrete.goonie.Mql5.utils;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Random;

public class Utils {

    public static double normalizeDouble(double value, int decimalPlaces) {
        String pattern = "0." + "0".repeat(decimalPlaces);
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String formattedValue = decimalFormat.format(value);
        try {
            return Double.parseDouble(String.valueOf(nf.parse(formattedValue).doubleValue()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateRandomTicket() {
        return new Random().nextInt(900000) + 100000;
    }

    public static void arraySetAsSeries(double[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            double temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }
}
