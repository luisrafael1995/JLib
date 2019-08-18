package pt.luisrafael1995.lib.collection;

import com.sun.istack.internal.NotNull;
import pt.luisrafael1995.lib.util.RandomPlus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class CollectionUtil {

    private CollectionUtil() {
    }

    private static <T> T getRandom(@NotNull List<T> list) {
        if (list.size() != 0) {
            int index = RandomPlus.getInstance().nextInt(list.size());
            return list.get(index);
        }
        return null;
    }

    public static <T> T getRandom(@NotNull Collection<T> collection) {
        boolean cast = collection instanceof List;
        List<T> list = cast ? (List<T>) collection : new ArrayList<>(collection);
        return getRandom(list);
    }

    public static <T> T getLast(@NotNull List<T> list) {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public static <T> T getFirst(@NotNull List<T> list) {
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static <T> boolean addUnique(@NotNull Collection<T> collection, @NotNull T object) {
        if (!collection.contains(object)) {
            collection.add(object);
            return true;
        }
        return false;
    }

    public static <T> boolean addUnique(@NotNull Collection<T> collection, @NotNull Collection<T> toAdd) {
        boolean added = false;
        for (T object : toAdd) {
            added |= addUnique(collection, object);
        }
        return added;
    }

    public static <T> boolean containsAllEach(@NotNull Collection<T> collection1, @NotNull Collection<T> collection2) {
        return collection1.containsAll(collection2) && collection2.containsAll(collection1);
    }

    /**
     * This method will do the same as {@link Map#getOrDefault} except that it will also set the {@code defaultValue}
     * in case the value is not set yet
     *
     * @param map          map to get and/or set the value
     * @param key          key of the value
     * @param defaultValue value to get as default and also set
     * @param <T>          key object type
     * @param <E>          value object type
     * @return the same as {@link Map#getOrDefault}
     */
    public static <T, E> E getOrDefaultSet(@NotNull Map<T, E> map, @NotNull T key, E defaultValue) {
        if (defaultValue != null && !map.containsKey(key)) {
            map.put(key, defaultValue);
        }
        return map.getOrDefault(key, defaultValue);
    }
}
