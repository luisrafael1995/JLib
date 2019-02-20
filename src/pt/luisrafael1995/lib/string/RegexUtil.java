package pt.luisrafael1995.lib.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {

    private RegexUtil() {
    }

    public static String[] findAll(String regex, String input) {
        return findAll(regex, input, 0);
    }

    public static String[] findAll(String regex, String input, int group) {
        try {
            List<String> found = new ArrayList<>();

            Pattern pattern = Pattern.compile(regex);
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
        String[] array = findAll(regex, input, group);
        return array.length > 0 ? array[0] : null;
    }

    public static String find(String regex, String input) {
        return find(regex, input, 0);
    }

    public static String findLast(String regex, String input, int group) {
        String[] array = findAll(regex, input, group);
        return array.length > 0 ? array[array.length - 1] : null;
    }

    public static String findLast(String regex, String input) {
        return findLast(regex, input, 0);
    }
}
