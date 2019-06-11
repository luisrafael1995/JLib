package pt.luisrafael1995.lib.text;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Regex {

    private static final String URL_CONTENT_REGEX = "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String HTTP_HTTPS_URL_REGEX = "https?://" + URL_CONTENT_REGEX;
    public static final String HTTP_URL_REGEX = "http://" + URL_CONTENT_REGEX;
    public static final String HTTPS_URL_REGEX = "https://" + URL_CONTENT_REGEX;
    public static final String FTP_URL_REGEX = "ftp://" + URL_CONTENT_REGEX;
    public static final String FILE_URL_REGEX = "file://" + URL_CONTENT_REGEX;
    public static final String URL_REGEX = "(https?|ftp|file)://" + URL_CONTENT_REGEX;

    public static final Flags UNIX_LINES = new Flags().unixLines().lock();
    public static final Flags CASE_INSENSITIVE = new Flags().caseInsensitive().lock();
    public static final Flags COMMENTS = new Flags().comments().lock();
    public static final Flags MULTILINE = new Flags().multiline().lock();
    public static final Flags LITERAL = new Flags().literal().lock();
    public static final Flags DOTALL = new Flags().dotAll().lock();
    public static final Flags UNICODE_CASE = new Flags().caseInsensitiveUnicode().lock();
    public static final Flags CANON_EQ = new Flags().canonicalEquivalence().lock();
    public static final Flags UNICODE_CHARACTER_CLASS = new Flags().unicodeCharacterClass().lock();

    private Regex() {
    }

    private static Pattern getPattern(@NotNull String regex, Flags flags) {
        try {
            int f = flags == null ? 0 : flags.flag;
            return Pattern.compile(regex, f);
        } catch (Exception ignore) {
            return Pattern.compile("(?!.*)"); // if it is not something
        }
    }

    private static Matcher getMatcher(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return getPattern(regex, flags).matcher(input);
    }

    public static String[] findAll(@NotNull String regex, @NotNull CharSequence input) {
        return findAll(regex, input, 0);
    }

    public static String[] findAll(@NotNull String regex, @NotNull CharSequence input, int group) {
        return findAll(regex, input, group, null);
    }

    public static String[] findAll(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return findAll(regex, input, 0, flags);
    }

    public static String[] findAll(@NotNull String regex, @NotNull CharSequence input, int group, Flags flags) {
        List<String> found = new ArrayList<>();
        Matcher matcher = getMatcher(regex, input, flags);
        while (matcher.find()) {
            found.add(matcher.group(group));
        }
        return found.toArray(new String[0]);
    }

    public static String find(@NotNull String regex, @NotNull CharSequence input) {
        return find(regex, input, 0);
    }

    public static String find(@NotNull String regex, @NotNull CharSequence input, int group) {
        return find(regex, input, group, null);
    }

    public static String find(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return find(regex, input, 0, flags);
    }

    public static String find(@NotNull String regex, @NotNull CharSequence input, int group, Flags flags) {
        Matcher matcher = getMatcher(regex, input, flags);
        return matcher.find() ? matcher.group(group) : null;
    }

    public static String findLast(@NotNull String regex, @NotNull CharSequence input) {
        return findLast(regex, input, 0);
    }

    public static String findLast(@NotNull String regex, @NotNull CharSequence input, int group) {
        return findLast(regex, input, group, null);
    }

    public static String findLast(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return findLast(regex, input, 0, flags);
    }

    public static String findLast(@NotNull String regex, @NotNull CharSequence input, int group, Flags flags) {
        String found = null;
        Matcher matcher = getMatcher(regex, input, flags);
        while (matcher.find()) {
            found = matcher.group(group);
        }
        return found;
    }

    public static boolean matches(@NotNull String regex, @NotNull CharSequence input) {
        return matches(regex, input, null);
    }

    public static boolean matches(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return getMatcher(regex, input, flags).matches();
    }

    public static String[] split(@NotNull String regex, @NotNull CharSequence input) {
        return split(regex, input, null);
    }

    public static String[] split(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return getPattern(regex, flags).split(input);
    }

    public static String replace(@NotNull String regex, @NotNull CharSequence input, String replacement) {
        return replace(regex, input, replacement, null);
    }

    public static String replace(@NotNull String regex, @NotNull CharSequence input, String replacement, Flags flags) {
        return getPattern(regex, flags).matcher(input).replaceFirst(replacement);
    }

    public static String replaceAll(@NotNull String regex, @NotNull CharSequence input, String replacement) {
        return replaceAll(regex, input, replacement, null);
    }

    public static String replaceAll(@NotNull String regex, @NotNull CharSequence input, String replacement, Flags flags) {
        return getPattern(regex, flags).matcher(input).replaceAll(replacement);
    }

    public static String remove(@NotNull String regex, @NotNull CharSequence input) {
        return remove(regex, input, null);
    }

    public static String remove(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return replace(regex, input, "", flags);
    }

    public static String removeAll(@NotNull String regex, @NotNull CharSequence input) {
        return removeAll(regex, input, null);
    }

    public static String removeAll(@NotNull String regex, @NotNull CharSequence input, Flags flags) {
        return replaceAll(regex, input, "", flags);
    }

    public static class Flags {

        private transient boolean locked = false;
        private int flag = 0;

        public Flags unixLines() {
            flag |= locked ? 0 : Pattern.UNIX_LINES;
            return this;
        }

        public Flags caseInsensitive() {
            flag |= locked ? 0 : Pattern.CASE_INSENSITIVE;
            return this;
        }

        public Flags comments() {
            flag |= locked ? 0 : Pattern.COMMENTS;
            return this;
        }

        public Flags multiline() {
            flag |= locked ? 0 : Pattern.MULTILINE;
            return this;
        }

        public Flags literal() {
            flag |= locked ? 0 : Pattern.LITERAL;
            return this;
        }

        public Flags dotAll() {
            flag |= locked ? 0 : Pattern.DOTALL;
            return this;
        }

        public Flags caseInsensitiveUnicode() {
            flag |= locked ? 0 : Pattern.UNICODE_CASE;
            return this;
        }

        public Flags canonicalEquivalence() {
            flag |= locked ? 0 : Pattern.CANON_EQ;
            return this;
        }

        public Flags unicodeCharacterClass() {
            flag |= locked ? 0 : Pattern.UNICODE_CHARACTER_CLASS;
            return this;
        }

        private Flags lock() {
            locked = true;
            return this;
        }

        public int value() {
            return flag;
        }
    }
}
