package pt.luisrafael1995.lib.collection;

import pt.luisrafael1995.lib.util.RandomPlus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CollectionUtil {

    private CollectionUtil() {
    }

    private static <T> T getRandom(List<T> list) {
        if (list != null && list.size() != 0) {
            int index = RandomPlus.getInstance().nextInt(list.size());
            return list.get(index);
        }
        return null;
    }

    public static <T> T getRandom(Collection<T> collection) {
        boolean cast = collection == null || collection instanceof List;
        List<T> list = cast ? (List<T>) collection : new ArrayList<>(collection);
        return getRandom(list);
    }

    public static <T> T getLast(List<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public static <T> T getFirst(List<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static <T> boolean addUnique(Collection<T> collection, T object) {
        if (collection != null && object != null && !collection.contains(object)) {
            collection.add(object);
            return true;
        }
        return false;
    }

    public static <T> boolean addUnique(Collection<T> collection, Collection<T> toAdd) {
        if (collection != null && toAdd != null) {
            boolean added = false;
            for (T object : toAdd) {
                added |= addUnique(collection, object);
            }
            return added;
        }
        return false;
    }

    public static <T> boolean containsAllEach(Collection<T> collection1, Collection<T> collection2) {
        return collection1 != null && collection2 != null &&
                collection1.containsAll(collection2) && collection2.containsAll(collection1);
    }
}
