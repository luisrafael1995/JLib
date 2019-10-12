package pt.luisrafael1995.lib.array;

import pt.luisrafael1995.lib.util.Extra;

import java.util.*;

public final class ArrayUtil {
    private ArrayUtil() {
    }

    // = = = COUNT = = =

    public static <T> int count(T[] array, T value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += Extra.equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(int[] array, int value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(short[] array, short value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(byte[] array, byte value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(long[] array, long value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(double[] array, double value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(float[] array, float value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(char[] array, char value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(boolean[] array, boolean value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += array[i] == value ? 1 : 0;
            }
        }
        return found;
    }

    public static <T> int count(T[][] array, T[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(int[][] array, int[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(short[][] array, short[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(byte[][] array, byte[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(long[][] array, long[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(double[][] array, double[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(float[][] array, float[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(char[][] array, char[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    public static int count(boolean[][] array, boolean[] value) {
        int found = -1;
        if (array != null) {
            found = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                found += equals(array[i], value) ? 1 : 0;
            }
        }
        return found;
    }

    // = = = NON DUPLICATED LENGTH = = =

    public static <T> int nonDuplicatedLength(T[] array) {
        return array == null ? -1 : new HashSet<>(Arrays.asList(array)).size();
    }

    public static int nonDuplicatedLength(int[] array) {
        if (array != null) {
            Set<Integer> set = new HashSet<>();
            for (int i : array) {
                set.add(i);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(short[] array) {
        if (array != null) {
            Set<Short> set = new HashSet<>();
            for (short s : array) {
                set.add(s);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(byte[] array) {
        if (array != null) {
            Set<Byte> set = new HashSet<>();
            for (byte b : array) {
                set.add(b);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(long[] array) {
        if (array != null) {
            Set<Long> set = new HashSet<>();
            for (long l : array) {
                set.add(l);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(double[] array) {
        if (array != null) {
            Set<Double> set = new HashSet<>();
            for (double d : array) {
                set.add(d);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(float[] array) {
        if (array != null) {
            Set<Float> set = new HashSet<>();
            for (float f : array) {
                set.add(f);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(char[] array) {
        if (array != null) {
            Set<Character> set = new HashSet<>();
            for (char c : array) {
                set.add(c);
            }
            return set.size();
        }
        return -1;
    }

    public static int nonDuplicatedLength(boolean[] array) {
        if (array != null) {
            Set<Boolean> set = new HashSet<>();
            for (boolean b : array) {
                set.add(b);
            }
            return set.size();
        }
        return -1;
    }

    // = = = INDEX OF = = =

    public static <T> int indexOf(T[] array, T value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (Extra.equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(int[] array, int value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(short[] array, short value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(long[] array, long value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(double[] array, double value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(float[] array, float value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(char[] array, char value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(boolean[] array, boolean value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <T> int indexOf(T[][] array, T[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(int[][] array, int[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(short[][] array, short[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(byte[][] array, byte[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(long[][] array, long[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(double[][] array, double[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(float[][] array, float[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(char[][] array, char[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(boolean[][] array, boolean[] value) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // = = = INDEXES OF = = =

    public static <T> int[] indexesOf(T[] array, T value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (Extra.equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(int[] array, int value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(short[] array, short value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(byte[] array, byte value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(long[] array, long value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(double[] array, double value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(float[] array, float value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(char[] array, char value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(boolean[] array, boolean value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (array[i] == value) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static <T> int[] indexesOf(T[][] array, T[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(int[][] array, int[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(short[][] array, short[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(byte[][] array, byte[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(long[][] array, long[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(double[][] array, double[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(float[][] array, float[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(char[][] array, char[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    public static int[] indexesOf(boolean[][] array, boolean[] value) {
        int[] indexes = null;
        if (array != null) {
            indexes = new int[count(array, value)];
            int index = 0;
            for (int i = 0; i < array.length && index < indexes.length; i++) {
                if (equals(array[i], value)) {
                    indexes[index++] = i;
                }
            }
        }
        return indexes;
    }

    // = = = LAST INDEX OF = = =

    public static <T> int lastIndexOf(T[] array, T value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (Extra.equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(float[] array, float value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(char[] array, char value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(boolean[] array, boolean value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <T> int lastIndexOf(T[][] array, T[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[][] array, int[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(short[][] array, short[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(byte[][] array, byte[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(long[][] array, long[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[][] array, double[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(float[][] array, float[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(char[][] array, char[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(boolean[][] array, boolean[] value) {
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (equals(array[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // = = = EQUALS = = =

    public static <T> boolean equals(T[] array1, T[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = Extra.equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(int[] array1, int[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(short[] array1, short[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(byte[] array1, byte[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(long[] array1, long[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(double[] array1, double[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(float[] array1, float[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(char[] array1, char[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(boolean[] array1, boolean[] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = (array1[i] == array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static <T> boolean equals(T[][] array1, T[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(int[][] array1, int[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(short[][] array1, short[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(byte[][] array1, byte[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(long[][] array1, long[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(double[][] array1, double[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(float[][] array1, float[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(char[][] array1, char[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    public static boolean equals(boolean[][] array1, boolean[][] array2) {
        boolean bothNull = array1 == null && array2 == null;
        boolean bothNotNull = array1 != null && array2 != null;
        boolean bothEquals = bothNotNull && array1.length == array2.length;
        if (bothEquals) {
            for (int i = 0; i < array1.length && bothEquals; i++) {
                bothEquals = equals(array1[i], array2[i]);
            }
        }
        return bothNull || bothEquals;
    }

    // = = = CONTAINS = = =

    public static <T> boolean contains(T[] array, T value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(byte[] array, byte value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(long[] array, long value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(double[] array, double value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(float[] array, float value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) != -1;
    }

    public static boolean contains(boolean[] array, boolean value) {
        return indexOf(array, value) != -1;
    }

    // = = = REPLACE = = =

    public static <T> void replace(T[] array, T oldValue, T newValue) {
        if (array != null && !Extra.equals(oldValue, newValue)) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(int[] array, int oldValue, int newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(short[] array, short oldValue, short newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(byte[] array, byte oldValue, byte newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(long[] array, long oldValue, long newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(double[] array, double oldValue, double newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(float[] array, float oldValue, float newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(char[] array, char oldValue, char newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replace(boolean[] array, boolean oldValue, boolean newValue) {
        if (array != null && oldValue != newValue) {
            int index = indexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    // = = = REPLACE ALL = = =

    public static <T> void replaceAll(T[] array, T oldValue, T newValue) {
        if (array != null && !Extra.equals(oldValue, newValue)) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(int[] array, int oldValue, int newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(short[] array, short oldValue, short newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(byte[] array, byte oldValue, byte newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(long[] array, long oldValue, long newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(double[] array, double oldValue, double newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(float[] array, float oldValue, float newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(char[] array, char oldValue, char newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceAll(boolean[] array, boolean oldValue, boolean newValue) {
        if (array != null && oldValue != newValue) {
            for (int index : indexesOf(array, oldValue)) {
                array[index] = newValue;
            }
        }
    }

    // = = = REPLACE LAST = = =

    public static <T> void replaceLast(T[] array, T oldValue, T newValue) {
        if (array != null && !Extra.equals(oldValue, newValue)) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(int[] array, int oldValue, int newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(short[] array, short oldValue, short newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(byte[] array, byte oldValue, byte newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(long[] array, long oldValue, long newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(double[] array, double oldValue, double newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(float[] array, float oldValue, float newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(char[] array, char oldValue, char newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    public static void replaceLast(boolean[] array, boolean oldValue, boolean newValue) {
        if (array != null && oldValue != newValue) {
            int index = lastIndexOf(array, oldValue);
            if (index != -1) {
                array[index] = newValue;
            }
        }
    }

    // = = = TO STRING = = =

    public static <T> String toString(T[] array) {
        return Arrays.toString(array);
    }

    public static <T> String toString(T[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(int[] array) {
        return Arrays.toString(array);
    }

    public static String toString(int[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(short[] array) {
        return Arrays.toString(array);
    }

    public static String toString(short[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(byte[] array) {
        return Arrays.toString(array);
    }

    public static String toString(byte[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(long[] array) {
        return Arrays.toString(array);
    }

    public static String toString(long[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(double[] array) {
        return Arrays.toString(array);
    }

    public static String toString(double[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(float[] array) {
        return Arrays.toString(array);
    }

    public static String toString(float[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(char[] array) {
        return Arrays.toString(array);
    }

    public static String toString(char[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    public static String toString(boolean[] array) {
        return Arrays.toString(array);
    }

    public static String toString(boolean[][] array) {
        if (array != null) {
            String[] toString = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                toString[i] = toString(array[i]);
            }
            return toString(toString);
        }
        return null;
    }

    // = = = COPY = = =

    /**
     * @return copied amount
     */
    public static <T> int copy(T[] src, int srcPos, T[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static <T> int copy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(int[] src, int srcPos, int[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(int[] src, int srcPos, int[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(short[] src, int srcPos, short[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(short[] src, int srcPos, short[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(byte[] src, int srcPos, byte[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(byte[] src, int srcPos, byte[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(long[] src, int srcPos, long[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(long[] src, int srcPos, long[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(double[] src, int srcPos, double[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(double[] src, int srcPos, double[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(float[] src, int srcPos, float[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(float[] src, int srcPos, float[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(char[] src, int srcPos, char[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(char[] src, int srcPos, char[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    /**
     * @return copied amount
     */
    public static int copy(boolean[] src, int srcPos, boolean[] dest, int destPos) {
        return copy(src, srcPos, dest, destPos, Integer.MAX_VALUE);
    }

    /**
     * @return copied amount
     */
    public static int copy(boolean[] src, int srcPos, boolean[] dest, int destPos, int length) {
        if (src != null && srcPos >= 0 && srcPos < src.length && dest != null && destPos >= 0 &&
                destPos < dest.length && length >= 0) {
            int realLength = Math.min(src.length - srcPos, dest.length - destPos);
            System.arraycopy(src, srcPos, dest, destPos, realLength);
            return realLength;
        }
        return -1;
    }

    public static <T> List<T> asList(T[] values) {
        List<T> list = new ArrayList<>();
        if (values != null) {
            list.addAll(Arrays.asList(values));
        }
        return list;
    }

    public static List<Integer> asList(int[] values) {
        Integer[] array = new Integer[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Short> asList(short[] values) {
        Short[] array = new Short[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Byte> asList(byte[] values) {
        Byte[] array = new Byte[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Long> asList(long[] values) {
        Long[] array = new Long[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Double> asList(double[] values) {
        Double[] array = new Double[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Float> asList(float[] values) {
        Float[] array = new Float[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Character> asList(char[] values) {
        Character[] array = new Character[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }

    public static List<Boolean> asList(boolean[] values) {
        Boolean[] array = new Boolean[values == null ? 0 : values.length];
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }
        }
        return asList(array);
    }
}
