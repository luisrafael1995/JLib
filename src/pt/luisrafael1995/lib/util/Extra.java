package pt.luisrafael1995.lib.util;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public final class Extra {

    private Extra() {
    }

    public static void sleep(long millis) {
        ignoreExceptions(() -> Thread.sleep(millis));
    }

    public static void sleep(long duration, TimeUnit unit) {
        sleep(Optional.of(unit).orElse(TimeUnit.MILLISECONDS).toMillis(duration));
    }

    public static void close(AutoCloseable closeable) {
        ignoreExceptions(closeable::close);
    }

    public static <T> boolean equals(T obj1, T obj2) {
        boolean bothNull = obj1 == null && obj2 == null;
        boolean bothNotNull = obj1 != null && obj2 != null;
        boolean bothEquals = bothNotNull && obj1.equals(obj2) && obj2.equals(obj1);

        return bothNull || bothEquals;
    }

    public static void ignoreExceptions(IgnoreExceptions ignore) {
        Optional.of(ignore).ifPresent(ignoreExceptions -> {
            try {
                ignoreExceptions.ignore();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        });
    }

    public interface IgnoreExceptions {
        void ignore() throws Exception;
    }
}
