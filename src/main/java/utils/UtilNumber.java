package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class UtilNumber {

    public static double round(double value, int places) {
        return round(value, places, RoundingMode.DOWN);
    }    

    public static double round(double value, int places, RoundingMode mode) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, mode);
        return bd.doubleValue();
    }
}
