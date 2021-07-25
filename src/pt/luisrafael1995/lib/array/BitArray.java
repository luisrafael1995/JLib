package pt.luisrafael1995.lib.array;

import java.util.Arrays;
import java.util.Iterator;

/**
 * The main focus of this class is memory efficiency.
 * This can easily replace a boolean[], with much more memory efficiency (and a longer array).
 * It can also replace other types of array, but it requires a some manipulation of those values.
 */
public class BitArray implements Iterable<Boolean> {

    private static final String NEGATIVE_SIZE = "negative size: %d";
    private static final String MAXIMUM_SIZE_REACHED = "maximum size reached: %d (max: %d)";

    private static final int RESERVED_BITS = 6;
    private static final long RESERVED_MASK = 0x3fL;

    private final long[] array;
    private transient long length = -1;

    public BitArray(long size) {
        if (size < 0) {
            throw new IllegalArgumentException(String.format(NEGATIVE_SIZE, size));
        }

        long rawSize = size / Long.SIZE;
        long semiSize = (int) (size % Long.SIZE);

        long arraySize = rawSize + (semiSize + RESERVED_BITS > Long.SIZE ? 2 : 1);

        if (arraySize > Integer.MAX_VALUE) {
            long maxSize = Integer.SIZE * ((long) Long.SIZE) - RESERVED_BITS;
            throw new IllegalArgumentException(String.format(MAXIMUM_SIZE_REACHED, size, maxSize));
        }

        array = new long[(int) arraySize];

        long offSet = arraySize * Long.SIZE - size - RESERVED_BITS;
        array[array.length - 1] &= ~offSet;
        array[array.length - 1] |= offSet;
    }

    public long length() {
        if (length < 0) {
            long offSet = array[array.length - 1] & RESERVED_MASK;
            length = ((long) array.length) * Long.SIZE - offSet - RESERVED_BITS;
        }
        return length;
    }

    private void checkIndex(long index) {
        if (index < 0 || index >= length()) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(index));
        }
    }

    public boolean get(long index) {
        checkIndex(index);

        int arrayIndex = (int) (index / Long.SIZE);
        int semiIndex = (int) (index % Long.SIZE);
        long mask = 0x01L << (Long.SIZE - semiIndex - 1);

        long value = mask & array[arrayIndex];
        return value != 0;
    }

    public void set(long index, boolean value) {
        checkIndex(index);

        int arrayIndex = (int) (index / Long.SIZE);
        int semiIndex = (int) (index % Long.SIZE);
        long mask = 0x01L << (Long.SIZE - semiIndex - 1);

        array[arrayIndex] &= ~mask;
        array[arrayIndex] |= value ? mask : 0;
    }

    public boolean has(boolean value) {
        for (boolean v : this) {
            if (v == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<Boolean>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length();
            }

            @Override
            public Boolean next() {
                return get(index++);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BitArray && Arrays.equals(array, ((BitArray) o).array);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (boolean b : this) {
            builder.append(b ? "1" : "0");
        }
        return builder.toString();
    }
}
