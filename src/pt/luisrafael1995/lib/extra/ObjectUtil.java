package pt.luisrafael1995.lib.extra;

public final class ObjectUtil {

    private ObjectUtil() {
    }

    public static void sleep(long millis) {
        ignoreExceptions(() -> Thread.sleep(millis));
    }

    public static void close(AutoCloseable closeable) {
        ignoreExceptions(closeable::close);
    }

    public static <T> boolean equals(T obj1, T obj2) {
        boolean bothNull = obj1 == null && obj2 == null;
        boolean bothNotNull = obj1 != null && obj2 != null;
        boolean bothEquals = bothNotNull && obj1.equals(obj2);

        return bothNull || bothEquals;
    }

    public static void ignoreExceptions(IgnoreExceptions ignore) {
        if (ignore != null) {
            try {
                ignore.ignore();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    public static <T> void ifNotNull(T obj, IfNotNull<T> ifNotNull) {
        if (ifNotNull != null && obj != null) {
            ifNotNull.ifNotNull(obj);
        }
    }

    public interface IgnoreExceptions {
        void ignore() throws Exception;
    }

    public interface IfNotNull<T> {
        void ifNotNull(T obj);
    }
}
