package pt.luisrafael1995.lib.util;

public class ObjectWrapper<T> {
    private T obj;

    public ObjectWrapper() {
        this(null);
    }

    public ObjectWrapper(T obj) {
        set(obj);
    }

    public synchronized void set(T obj) {
        this.obj = obj;
    }

    public synchronized T get() {
        return obj;
    }

    public synchronized boolean isEmpty() {
        return obj == null;
    }

    public void run(Runnable<T> runnable) {
        T obj = get();
        if (obj != null) {
            runnable.run(obj);
        }
    }

    public interface Runnable<T> {
        void run(T obj);
    }
}
