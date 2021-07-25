package pt.luisrafael1995.lib.counter;

public class IntCounter {

    private final int defaultValue;
    private int count;

    public IntCounter() {
        this(0);
    }

    public IntCounter(int value) {
        this.defaultValue = value;
        count = value;
    }

    public synchronized int get() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized void increment(int amount) {
        count += amount;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized void decrement(int amount) {
        count -= amount;
    }

    public synchronized void reset() {
        count = defaultValue;
    }
}
