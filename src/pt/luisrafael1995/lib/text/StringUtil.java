package pt.luisrafael1995.lib.text;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class StringUtil {

    private static final String HEXADECIMAL = "0123456789ABCDEF";

    private static final Map<String, Byte> hexaCache = new HashMap<>();

    private StringUtil() {
    }

    public static byte[] getBytes(String string) {
        return getBytes(string, StandardCharsets.UTF_8);
    }

    public static byte[] getBytes(String string, Charset charset) {
        return string == null ? null : string.getBytes(charset);
    }

    public static String toString(byte[] bytes) {
        return toString(bytes, StandardCharsets.UTF_8);
    }

    public static String toString(byte[] bytes, Charset charset) {
        return bytes == null ? null : new String(bytes, charset);
    }

    public static String bytesToHexa(byte[] bytes) {
        return bytesToHexa(bytes, false);
    }

    public static String bytesToHexa(byte[] bytes, boolean upperCase) {
        if (bytes == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format(upperCase ? "%02X" : "%02x", b));
        }

        return builder.toString();
    }

    public static byte[] hexaToBytes(String hexa) {
        if (hexa == null) {
            return null;
        }

        if (hexa.length() % 2 != 0) {
            return hexaToBytes(String.format("0%s", hexa));
        }

        byte[] bytes = new byte[hexa.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int hexaIndex = i * 2;
            bytes[i] = getHexaByte(hexa.substring(hexaIndex, hexaIndex + 2));
        }
        return bytes;
    }

    private static byte getHexaByte(String hexaByte) {
        hexaByte = hexaByte.toUpperCase();

        if (!hexaCache.containsKey(hexaByte)) {
            int value1 = HEXADECIMAL.indexOf(hexaByte.charAt(0));
            int value2 = HEXADECIMAL.indexOf(hexaByte.charAt(1));
            if (value1 < 0 || value2 < 0) {
                throw new RuntimeException("Invalid hexabyte representation");
            }
            byte b = (byte) (((value1 << 4) | value2) & 0x0ff);
            hexaCache.put(hexaByte, b);
        }

        return hexaCache.get(hexaByte);
    }

    public static boolean equalsIgnoreCase(String string1, String string2) {
        boolean bothNull = string1 == null && string2 == null;
        boolean bothNotNull = string1 != null && string2 != null;
        boolean bothEquals = bothNotNull && string1.equalsIgnoreCase(string2);

        return bothNull || bothEquals;
    }
}
