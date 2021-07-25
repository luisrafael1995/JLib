package pt.luisrafael1995.lib.random;

import java.util.Random;

public class RandomPlus extends Random {

    private static final String BadBound = "bound must be positive";
    private static final String BadFromToRange = "'to' must be greater then 'from'";

    private static final RandomPlus INSTANCE = new RandomPlus();

    public static RandomPlus getInstance() {
        return INSTANCE;
    }

    /**
     * @return the next positive pseudorandom, uniformly distributed {@code int}
     * value from this random number generator's sequence
     */
    public int nextPositiveInt() {
        return next(31);
    }

    /**
     * @return the next negative pseudorandom, uniformly distributed {@code int}
     * value from this random number generator's sequence
     */
    public int nextNegativeInt() {
        return (1 << 31) + nextPositiveInt();
    }

    /**
     * @param from the upper bound (inclusive).  Must be smaller than {@code to}.
     * @param to   the lower bound (exclusive).  Must be bigger than {@code from}.
     * @return the next pseudorandom, uniformly distributed {@code int}
     * value between {@code from} (inclusive) and {@code to} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if from is bigger or equals than to
     */
    public int nextInt(int from, int to) {
        if (from >= to) {
            throw new IllegalArgumentException(BadFromToRange);
        }
        return from + nextInt(to - from);
    }

    /**
     * @return the next positive pseudorandom, uniformly distributed {@code long}
     * value from this random number generator's sequence
     */
    public long nextPositiveLong() {
        return ((long) (next(31)) << 32) + next(32);
    }

    /**
     * @return the next negative pseudorandom, uniformly distributed {@code long}
     * value from this random number generator's sequence
     */
    public long nextNegativeLong() {
        return (1L << 63) + nextPositiveLong();
    }

    /**
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return the next pseudorandom, uniformly distributed {@code long}
     * value between zero (inclusive) and {@code bound} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if bound is not positive
     */
    public long nextLong(long bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException(BadBound);
        }
        return nextPositiveLong() % bound;
    }

    /**
     * @param from the upper bound (inclusive).  Must be smaller than {@code to}.
     * @param to   the lower bound (exclusive).  Must be bigger than {@code from}.
     * @return the next pseudorandom, uniformly distributed {@code long}
     * value between {@code from} (inclusive) and {@code to} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if from is bigger or equals than to
     */
    public long nextLong(long from, long to) {
        if (from >= to) {
            throw new IllegalArgumentException(BadFromToRange);
        }
        return from + nextLong(to - from);
    }

    /**
     * @return the next pseudorandom, uniformly distributed {@code float}
     * value from this random number generator's sequence
     */
    public float nextFullFloat() {
        return nextInt() + nextFloat();
    }

    /**
     * @return the next positive pseudorandom, uniformly distributed {@code float}
     * value from this random number generator's sequence
     */
    public float nextPositiveFloat() {
        return nextPositiveInt() + nextFloat();
    }

    /**
     * @return the next negative pseudorandom, uniformly distributed {@code float}
     * value from this random number generator's sequence
     */
    public float nextNegativeFloat() {
        return nextNegativeInt() + nextFloat();
    }

    /**
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return the next pseudorandom, uniformly distributed {@code float}
     * value between zero (inclusive) and {@code bound} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if bound is not positive
     */
    public float nextFullFloat(float bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException(BadBound);
        }
        if (bound == (int) bound) {
            return nextInt((int) bound) + nextFloat();
        }
        return nextPositiveFloat() % bound;
    }

    /**
     * @param from the upper bound (inclusive).  Must be smaller than {@code to}.
     * @param to   the lower bound (exclusive).  Must be bigger than {@code from}.
     * @return the next pseudorandom, uniformly distributed {@code float}
     * value between {@code from} (inclusive) and {@code to} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if from is bigger or equals than to
     */
    public float nextFullFloat(float from, float to) {
        if (from >= to) {
            throw new IllegalArgumentException(BadFromToRange);
        }
        return from + nextFullFloat(to - from);
    }

    /**
     * @return the next pseudorandom, uniformly distributed {@code double}
     * value from this random number generator's sequence
     */
    public double nextFullDouble() {
        return nextLong() + nextDouble();
    }

    /**
     * @return the next positive pseudorandom, uniformly distributed {@code double}
     * value from this random number generator's sequence
     */
    public double nextPositiveDouble() {
        return nextPositiveLong() + nextDouble();
    }

    /**
     * @return the next negative pseudorandom, uniformly distributed {@code double}
     * value from this random number generator's sequence
     */
    public double nextNegativeDouble() {
        return nextNegativeLong() + nextDouble();
    }

    /**
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return the next pseudorandom, uniformly distributed {@code double}
     * value between zero (inclusive) and {@code bound} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if bound is not positive
     */
    public double nextFullDouble(double bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException(BadBound);
        }
        if (bound == (long) bound) {
            return nextLong((long) bound) + nextDouble();
        }
        return nextPositiveDouble() % bound;
    }

    /**
     * @param from the upper bound (inclusive).  Must be smaller than {@code to}.
     * @param to   the lower bound (exclusive).  Must be bigger than {@code from}.
     * @return the next pseudorandom, uniformly distributed {@code double}
     * value between {@code from} (inclusive) and {@code to} (exclusive)
     * from this random number generator's sequence
     * @throws IllegalArgumentException if from is bigger or equals than to
     */
    public double nextFullFloat(double from, double to) {
        if (from >= to) {
            throw new IllegalArgumentException(BadFromToRange);
        }
        return from + nextFullDouble(to - from);
    }
}
