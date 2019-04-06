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
        return bytesLength(bytes, true);
    }

    /**
     * @param bytes amount
     * @param si    true corresponds to bi bytes (1024), false corresponds to regular (1000)
     * @return string with the amount of bytes
     */
    public static String bytesLength(long bytes, boolean si) {
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

        String formattedValue = maxMinDecimalFormat(valueToBeFormatted);
        return String.format("%s %s", formattedValue, scale[exp]);
    }

    public static String maxMinDecimalFormat(double number) {
        return maxMinDecimalFormat(number, false);
    }

    public static String maxMinDecimalFormat(double number, int maxDecimalDigits) {
        return maxMinDecimalFormat(number, maxDecimalDigits, false);
    }

    public static String maxMinDecimalFormat(double number, boolean group) {
        return maxMinDecimalFormat(number, 2, group);
    }

    public static String maxMinDecimalFormat(double number, int maxDecimalDigits, boolean group) {
        if (maxDecimalDigits < 0) {
            return maxMinDecimalFormat(number, group);
        }

        double tmpNumber = number;
        int decimalNumbers = maxDecimalDigits;
        while (tmpNumber >= 10 && decimalNumbers > 0) {
            decimalNumbers--;
            tmpNumber /= 10;
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(decimalNumbers);
        nf.setMinimumFractionDigits(decimalNumbers);
        nf.setGroupingUsed(true);
        nf.setRoundingMode(RoundingMode.FLOOR);

        return nf.format(number);
    }
}
