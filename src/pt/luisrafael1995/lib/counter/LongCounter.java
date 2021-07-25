package pt.luisrafael1995.lib.counter;

public class LongCounter {

    private final long defaultValue;
    private long count;

    public LongCounter() {
        this(0);
    }

    public LongCounter(long value) {
        this.defaultValue = value;
        count = value;
    }

    public synchronized long get() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized void increment(long amount) {
        count += amount;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized void decrement(long amount) {
        count -= amount;
    }

    public synchronized void reset() {
        count = defaultValue;
    }
}
