package pt.luisrafael1995.lib.text;

import java.math.RoundingMode;
import java.text.NumberFormat;

public final class FormatUtil {

    private static final int BYTE_SIZE_UNIT = 1024;
    private static final String[] BYTE_SIZE_SCALE = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};

    private FormatUtil() {
    }

    public static String humanReadableBytes(long bytes) {
        if (bytes < 0) {
            return humanReadableBytes(0);
        }

        if (bytes < BYTE_SIZE_UNIT) {
            return String.format("%s %s", bytes, BYTE_SIZE_SCALE[0]);
        }

        int exp = (int) (Math.log(bytes) / Math.log(BYTE_SIZE_UNIT));
        exp += bytes >= 1000 * Math.pow(BYTE_SIZE_UNIT, exp) ? 1 : 0; // windows file property look
        double valueToBeFormatted = bytes / Math.pow(BYTE_SIZE_UNIT, exp);

        String formattedValue = maxMinDecimalFormat(valueToBeFormatted);
        return String.format("%s %s", formattedValue, BYTE_SIZE_SCALE[exp]);
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
