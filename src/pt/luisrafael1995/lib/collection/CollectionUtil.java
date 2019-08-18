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

    public static <T, E> E getOrDefaultSet(@NotNull Map<T, E> map, @NotNull T key, E defaultValue) {
        if (defaultValue != null && !map.containsKey(key)) {
            map.put(key, defaultValue);
        }
        return map.getOrDefault(key, defaultValue);
    }
}
