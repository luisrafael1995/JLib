package pt.luisrafael1995.lib.thread;

import static pt.luisrafael1995.lib.thread.ThreadManager.availableProcessors;

public final class GlobalThreadManager {

    private static final ThreadManager quickManager = new ThreadManager(true);
    private static final ThreadManager manager = new ThreadManager(availableProcessors * 4);

    private GlobalThreadManager() {
    }

    public static void quickExecute(Runnable runnable) {
        quickManager.execute(runnable);
    }

    public static void quickSchedule(Runnable runnable, long delayMillis) {
        quickManager.schedule(runnable, delayMillis);
    }

    public static void execute(Runnable runnable) {
        manager.execute(runnable);
    }

    public static void schedule(Runnable runnable, long delayMillis) {
        manager.schedule(runnable, delayMillis);
    }
}
