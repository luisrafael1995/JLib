package pt.luisrafael1995.lib.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {

    private static final String URL_CONTENT_REGEX = "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String HTTP_HTTPS_URL_REGEX = "https?://" + URL_CONTENT_REGEX;
    public static final String HTTP_URL_REGEX = "http://" + URL_CONTENT_REGEX;
    public static final String HTTPS_URL_REGEX = "https://" + URL_CONTENT_REGEX;
    public static final String FTP_URL_REGEX = "ftp://" + URL_CONTENT_REGEX;
    public static final String FILE_URL_REGEX = "file://" + URL_CONTENT_REGEX;
    public static final String URL_REGEX = "(https?|ftp|file)://" + URL_CONTENT_REGEX;

    private RegexUtil() {
    }

    public static String[] findAll(String regex, String input) {
        return findAll(regex, input, 0);
    }

    public static String[] findAll(String regex, String input, int group) {
        return findAll(regex, input, group, true);
    }

    public static String[] findAll(String regex, String input, boolean caseSensitive) {
        return findAll(regex, input, 0, caseSensitive);
    }

    public static String[] findAll(String regex, String input, int group, boolean caseSensitive) {
        try {
            List<String> found = new ArrayList<>();

            int flags = 0;
            flags |= caseSensitive ? 0 : Pattern.CASE_INSENSITIVE;
            Pattern pattern = Pattern.compile(regex, flags);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                found.add(matcher.group(group));
            }

            return found.toArray(new String[0]);
        } catch (Exception e) {
            //e.printStackTrace();
            return new String[0];
        }
    }

    public static String find(String regex, String input, int group) {
        return find(regex, input, group, true);
    }

    public static String find(String regex, String input) {
        return find(regex, input, 0);
    }

    public static String find(String regex, String input, int group, boolean caseSensitive) {
        String[] array = findAll(regex, input, group, caseSensitive);
        return array.length > 0 ? array[0] : null;
    }

    public static String find(String regex, String input, boolean caseSensitive) {
        return find(regex, input, 0, caseSensitive);
    }

    public static String findLast(String regex, String input, int group) {
        return findLast(regex, input, group, true);
    }

    public static String findLast(String regex, String input) {
        return findLast(regex, input, 0);
    }

    public static String findLast(String regex, String input, int group, boolean caseSensitive) {
        String[] array = findAll(regex, input, group, caseSensitive);
        return array.length > 0 ? array[array.length - 1] : null;
    }

    public static String findLast(String regex, String input, boolean caseSensitive) {
        return findLast(regex, input, 0, caseSensitive);
    }

    public static boolean contains(String regex, String input, int group) {
        return contains(regex, input, group, true);
    }

    public static boolean contains(String regex, String input) {
        return contains(regex, input, 0);
    }

    public static boolean contains(String regex, String input, int group, boolean caseSensitive) {
        return findAll(regex, input, group, caseSensitive).length > 0;
    }

    public static boolean contains(String regex, String input, boolean caseSensitive) {
        return contains(regex, input, 0, caseSensitive);
    }
}
