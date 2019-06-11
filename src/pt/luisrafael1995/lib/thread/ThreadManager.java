package pt.luisrafael1995.lib.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    public static final int availableProcessors = Runtime.getRuntime().availableProcessors();

    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    public ThreadManager() {
        this(false);
    }

    public ThreadManager(boolean highPriority) {
        this(availableProcessors, highPriority);
    }

    public ThreadManager(int threadsNumber) {
        this(threadsNumber > 0 ? threadsNumber : availableProcessors, false);
    }

    public ThreadManager(int threadsNumber, boolean highPriority) {
        threadPoolExecutor = new ScheduledThreadPoolExecutor(threadsNumber, highPriority ?
                Executors.privilegedThreadFactory() :
                Executors.defaultThreadFactory());
        threadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        threadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        threadPoolExecutor.setKeepAliveTime(2, TimeUnit.SECONDS);
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void execute(Runnable runnable) {
        if (runnable != null && isOperational()) {
            threadPoolExecutor.execute(runnable);
        }
    }

    public void schedule(Runnable runnable, long delay) {
        schedule(runnable, delay, null);
    }

    public void schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        if (runnable != null && isOperational()) {
            timeUnit = timeUnit == null ? TimeUnit.MILLISECONDS : timeUnit;
            delay = delay < 0 ? 0 : delay;
            threadPoolExecutor.schedule(runnable, delay, timeUnit);
        }
    }

    public void schedule(Runnable runnable, Date startAt) {
        schedule(runnable, startAt, null);
    }

    public void schedule(Runnable runnable, Date startAt, TimeUnit timeUnit) {
        long delay = startAt.getTime() - new Date().getTime();
        schedule(runnable, delay, timeUnit);
    }

    public void scheduleAtFixedRate(Runnable runnable) {
        scheduleAtFixedRate(runnable, 0);
    }

    public void scheduleAtFixedRate(Runnable runnable, long period) {
        scheduleAtFixedRate(runnable, period, null);
    }

    public void scheduleAtFixedRate(Runnable runnable, long period, TimeUnit timeUnit) {
        scheduleAtFixedRate(runnable, 0, period, timeUnit);
    }

    public void scheduleAtFixedRate(Runnable runnable, long delay, long period) {
        scheduleAtFixedRate(runnable, delay, period, null);
    }

    public void scheduleAtFixedRate(Runnable runnable, long delay, long period, TimeUnit timeUnit) {
        if (runnable != null && isOperational()) {
            delay = delay < 0 ? 0 : delay;
            period = period < 0 ? 0 : period;
            timeUnit = timeUnit == null ? TimeUnit.MILLISECONDS : timeUnit;
            threadPoolExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
        }
    }

    public void scheduleAtFixedRate(Runnable runnable, Date startAt, long period) {
        scheduleAtFixedRate(runnable, startAt, period, null);
    }

    public void scheduleAtFixedRate(Runnable runnable, Date startAt, long period, TimeUnit timeUnit) {
        if (startAt != null) {
            long delay = startAt.getTime() - new Date().getTime();
            scheduleAtFixedRate(runnable, delay, period, timeUnit);
        } else {
            scheduleAtFixedRate(runnable, period, timeUnit);
        }
    }

    public void shutdownNow() {
        threadPoolExecutor.shutdownNow();
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
        if (!awaitTermination()) {
            threadPoolExecutor.shutdownNow();
            awaitTermination();
        }
    }

    private boolean awaitTermination() {
        try {
            return threadPoolExecutor.awaitTermination(7, TimeUnit.SECONDS);
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isOperational() {
        return !(threadPoolExecutor.isShutdown() || threadPoolExecutor.isTerminated() ||
                threadPoolExecutor.isTerminating());
    }
}
