package pt.luisrafael1995.lib.text;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

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

    public static String bytesLength(long bytes, Locale locale) {
        return bytesLength(bytes, false, locale);
    }

    public static String bytesLength(long bytes, boolean si, int decimalPlaces) {
        return bytesLength(bytes, si, decimalPlaces, true);
    }

    public static String bytesLength(long bytes, boolean si, boolean dynamic) {
        return bytesLength(bytes, si, 2, dynamic);
    }

    public static String bytesLength(long bytes, int decimalPlaces, boolean dynamic) {
        return bytesLength(bytes, false, decimalPlaces, dynamic);
    }

    public static String bytesLength(long bytes, boolean si, Locale locale) {
        return bytesLength(bytes, si, true, locale);
    }

    public static String bytesLength(long bytes, int decimalPlaces, Locale locale) {
        return bytesLength(bytes, false, decimalPlaces, locale);
    }

    public static String bytesLength(long bytes, boolean si, int decimalPlaces, boolean dynamic) {
        return bytesLength(bytes, si, decimalPlaces, dynamic, null);
    }

    public static String bytesLength(long bytes, boolean si, int decimalPlaces, Locale locale) {
        return bytesLength(bytes, si, decimalPlaces, true, locale);
    }

    public static String bytesLength(long bytes, boolean si, boolean dynamic, Locale locale) {
        return bytesLength(bytes, si, 2, dynamic, locale);
    }

    public static String bytesLength(long bytes, int decimalPlaces, boolean dynamic, Locale locale) {
        return bytesLength(bytes, false, decimalPlaces, dynamic, locale);
    }

    /**
     * @param bytes         amount to be formatted
     * @param si            true corresponds to bi bytes (1024), false corresponds to regular (1000)
     * @param decimalPlaces amount of decimal places {@see FormatUtil#numberDecimalFormat}
     * @param dynamic       amount of decimal places depends on absolute value {@see FormatUtil#numberDecimalFormat}
     * @param locale        locale for format {@see FormatUtil#numberDecimalFormat}
     * @return string formatted with the amount of bytes and bytes unit
     */
    public static String bytesLength(long bytes, boolean si, int decimalPlaces, boolean dynamic, Locale locale) {
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

        String formattedValue = numberDecimal(valueToBeFormatted, decimalPlaces, dynamic, locale);
        return String.format("%s %s", formattedValue, scale[exp]);
    }

    public static String numberDecimal(double number) {
        return numberDecimal(number, false);
    }

    public static String numberDecimal(double number, boolean group) {
        return numberDecimal(number, group, false);
    }

    public static String numberDecimal(double number, Locale locale) {
        return numberDecimal(number, false, locale);
    }

    public static String numberDecimal(double number, int decimalPlaces) {
        return numberDecimal(number, decimalPlaces, false);
    }

    public static String numberDecimal(double number, boolean group, int decimalPlaces) {
        return numberDecimal(number, group, decimalPlaces, false);
    }

    public static String numberDecimal(double number, boolean group, boolean dynamic) {
        return numberDecimal(number, group, 3, dynamic);
    }

    public static String numberDecimal(double number, int decimalPlaces, boolean dynamic) {
        return numberDecimal(number, false, decimalPlaces, dynamic);
    }

    public static String numberDecimal(double number, boolean group, Locale locale) {
        return numberDecimal(number, group, false, locale);
    }

    public static String numberDecimal(double number, int decimalPlaces, Locale locale) {
        return numberDecimal(number, decimalPlaces, false, locale);
    }

    public static String numberDecimal(double number, boolean group, int decimalPlaces, boolean dynamic) {
        return numberDecimal(number, group, decimalPlaces, dynamic, null);
    }

    public static String numberDecimal(double number, boolean group, int decimalPlaces, Locale locale) {
        return numberDecimal(number, group, decimalPlaces, false, locale);
    }

    public static String numberDecimal(double number, boolean group, boolean dynamic, Locale locale) {
        return numberDecimal(number, group, 3, dynamic, locale);
    }

    public static String numberDecimal(double number, int decimalPlaces, boolean dynamic, Locale locale) {
        return numberDecimal(number, false, decimalPlaces, dynamic, locale);
    }

    /**
     * @param number        to be formatted
     * @param group         add commas to formatted number
     * @param decimalPlaces number of decimal places
     * @param dynamic       amount of decimal places will depend on the absolute value
     * @param locale        locale for format
     * @return formatted number
     */
    public static String numberDecimal(double number, boolean group, int decimalPlaces, boolean dynamic, Locale locale) {
        if (decimalPlaces < 0) {
            return numberDecimal(number, dynamic, group);
        }

        if (locale == null) {
            locale = Locale.getDefault();
        }

        double tmpNumber = number;
        int decimalNumbers = decimalPlaces;
        if (dynamic) {
            while (tmpNumber >= 10 && decimalNumbers > 0) {
                decimalNumbers--;
                tmpNumber /= 10;
            }
        }

        NumberFormat nf = NumberFormat.getInstance(locale);
        nf.setMaximumFractionDigits(decimalNumbers);
        nf.setMinimumFractionDigits(decimalNumbers);
        nf.setGroupingUsed(group);
        nf.setRoundingMode(RoundingMode.FLOOR);

        return nf.format(number);
    }
}
