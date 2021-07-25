package pt.luisrafael1995.lib.util;

import java.util.concurrent.TimeUnit;

public final class Extra {

    private Extra() {
    }

    public static void sleep(long millis) {
        ignoreExceptions(() -> Thread.sleep(millis));
    }

    public static void sleep(long duration, TimeUnit unit) {
        unit = unit == null ? TimeUnit.MILLISECONDS : unit;
        sleep(unit.toMillis(duration));
    }

    public static void close(AutoCloseable closeable) {
        ignoreExceptions(closeable::close);
    }

    public static <T> boolean equals(T obj1, T obj2) {
        boolean bothNull = obj1 == null && obj2 == null;
        boolean bothNotNull = obj1 != null && obj2 != null;
        boolean bothEquals = bothNotNull && obj1.equals(obj2) && obj2.equals(obj1);

        return bothNull || bothEquals;
//        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }

    public static void ignoreExceptions(Runnable runnable) {
        ignoreExceptions(() -> {
            runnable.run();
            return null;
        });
    }

    public static <T> T ignoreExceptions(Returnable<T> returnable) {
        return ignoreExceptions(returnable, null);
    }

    public static <T> T ignoreExceptions(Returnable<T> returnable, T defValue) {
        try {
            return returnable.run();
        } catch (Throwable ignore) {
        }
        return defValue;
    }

    public interface Runnable {
        void run() throws Throwable;
    }

    public interface Returnable<T> {
        T run() throws Throwable;
    }
}
