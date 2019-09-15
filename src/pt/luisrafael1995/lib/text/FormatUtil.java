package pt.luisrafael1995.lib.text;

import java.math.RoundingMode;
import java.text.NumberFormat;

public final class FormatUtil {

    private static final int BYTE_SIZE_UNIT = 1000;
    private static final int BI_BYTE_SIZE_UNIT = 1024;
    private static final String[] BYTE_SIZE_SCALE = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
    private static final String[] BI_BYTE_SIZE_SCALE = new String[]{"B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"};

    private FormatUtil() {
    }

    public static String bytesLength(long bytes) {
        return bytesLength(bytes, false);
    }

    public static String bytesLength(long bytes, boolean si) {
        return bytesLength(bytes, si, true);
    }

    public static String bytesLength(long bytes, int decimalPlaces) {
        return bytesLength(bytes, false, decimalPlaces);
    }

    public static String bytesLength(long bytes, boolean si, int decimalPlaces) {
        return bytesLength(bytes, si, decimalPlaces, true);
    }

    public static String bytesLength(long bytes, boolean si, boolean dynamicDecimalPlaces) {
        return bytesLength(bytes, si, 2, dynamicDecimalPlaces);
    }

    public static String bytesLength(long bytes, int decimalPlaces, boolean dynamicDecimalPlaces) {
        return bytesLength(bytes, false, decimalPlaces, dynamicDecimalPlaces);
    }

    /**
     * @param bytes                amount to be formatted
     * @param si                   true corresponds to bi bytes (1024), false corresponds to regular (1000)
     * @param decimalPlaces        amount of decimal places {@see FormatUtil#numberDecimalFormat(double, int, boolean, boolean)}
     * @param dynamicDecimalPlaces amount of decimal places depends on absolute value {@see FormatUtil#numberDecimalFormat(double, int, boolean, boolean)}
     * @return string formatted with the amount of bytes and bytes unit
     */
    public static String bytesLength(long bytes, boolean si, int decimalPlaces, boolean dynamicDecimalPlaces) {
        if (bytes < 0) {
            return bytesLength(0, si);
        }

        int unit = si ? BI_BYTE_SIZE_UNIT : BYTE_SIZE_UNIT;
        String[] scale = si ? BI_BYTE_SIZE_SCALE : BYTE_SIZE_SCALE;

        if (bytes < unit) {
            return String.format("%s %s", bytes, scale[0]);
        }

        int exp = (int) (Math.log(bytes) / Math.log(unit));
        exp += bytes >= 1000 * Math.pow(unit, exp) ? 1 : 0; // windows file property look (in case of si = true)
        double valueToBeFormatted = bytes / Math.pow(unit, exp);

        String formattedValue = numberDecimal(valueToBeFormatted, decimalPlaces, dynamicDecimalPlaces);
        return String.format("%s %s", formattedValue, scale[exp]);
    }

    public static String numberDecimal(double number) {
        return numberDecimal(number, false);
    }

    public static String numberDecimal(double number, boolean group) {
        return numberDecimal(number, group, false);
    }

    public static String numberDecimal(double number, int decimalPlaces) {
        return numberDecimal(number, decimalPlaces, false);
    }

    public static String numberDecimal(double number, boolean group, int decimalPlaces) {
        return numberDecimal(number, group, decimalPlaces, false);
    }

    public static String numberDecimal(double number, boolean group, boolean dynamicDecimalPlaces) {
        return numberDecimal(number, group, 3, dynamicDecimalPlaces);
    }

    public static String numberDecimal(double number, int decimalPlaces, boolean dynamicDecimalPlaces) {
        return numberDecimal(number, false, decimalPlaces, dynamicDecimalPlaces);
    }

    /**
     * @param number               to be formated
     * @param group                add commas to formatted number
     * @param decimalPlaces        number of decimal places
     * @param dynamicDecimalPlaces amount of decimal places will depend on the absolute value
     * @return formatted number
     */
    public static String numberDecimal(double number, boolean group, int decimalPlaces, boolean dynamicDecimalPlaces) {
        if (decimalPlaces < 0) {
            return numberDecimal(number, dynamicDecimalPlaces, group);
        }

        double tmpNumber = number;
        int decimalNumbers = decimalPlaces;
        if (dynamicDecimalPlaces) {
            while (tmpNumber >= 10 && decimalNumbers > 0) {
                decimalNumbers--;
                tmpNumber /= 10;
            }
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(decimalNumbers);
        nf.setMinimumFractionDigits(decimalNumbers);
        nf.setGroupingUsed(group);
        nf.setRoundingMode(RoundingMode.FLOOR);

        return nf.format(number);
    }
}
