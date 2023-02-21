package org.example.CheckerDoubleValue;

//import org.apache.commons.lang3.math.NumberUtils;
import org.example.CheckerDoubleValue.CheckerDouble;
public  class CheckerDouble {
    public static boolean IsNumberFormat(String s) {
        try {
            Double.parseDouble(s);
//            NumberUtils.isCreatable(s);
            Double.isInfinite(Double.parseDouble(s));
            Double.isFinite(Double.parseDouble(s));
            Double.isNaN(Double.parseDouble(s));

            return (s != null && s.matches("[-+]?\\d*\\.?\\d+"));
        } catch (NumberFormatException e) {
            return  false;
        }
    }
}
