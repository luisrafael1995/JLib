package pt.luisrafael1995.lib.collection;

import pt.luisrafael1995.lib.util.Extra;
import pt.luisrafael1995.lib.util.RandomPlus;

import java.util.*;

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
        List<T> list = null;
        if (collection != null) {
            boolean cast = collection instanceof List;
            list = cast ? (List<T>) collection : new ArrayList<>(collection);
        }
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
        if (collection != null && !collection.contains(object)) {
            collection.add(object);
            return true;
        }
        return false;
    }

    public static <T> boolean addUnique(Collection<T> collection, Collection<T> toAdd) {
        boolean added = false;
        for (T object : toAdd) {
            added |= addUnique(collection, object);
        }
        return added;
    }

    public static <T> boolean containsAllEach(Collection<T> collection1, Collection<T> collection2) {
        return collection1 != null && collection2 != null && collection1.containsAll(collection2) &&
                collection2.containsAll(collection1);
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
    public static <T, E> E getOrDefaultSet(Map<T, E> map, T key, E defaultValue) {
        if (map != null) {
            if (!map.containsKey(key)) {
                map.put(key, defaultValue);
            }
            return map.getOrDefault(key, defaultValue);
        }
        return defaultValue;
    }

    public static <T> boolean hasDuplicatedValues(List<T> list) {
        if (list != null) {
            for (T t : list) {
                int firstIndex = list.indexOf(t);
                int lastIndex = list.lastIndexOf(t);
                if (firstIndex != lastIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> void removeDuplicatedValues(List<T> list) {
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; i--) {
                T t = list.get(i);
                int index = list.indexOf(t);
                if (index != i) {
                    list.remove(i);
                }
            }
        }
    }

    public static <T, E> List<T> convertList(List<E> list, ObjectConverter<T, E> converter) {
        List<T> converted = null;
        if (list != null) {
            converted = new ArrayList<>();
            copyAndConvert(list, converted, converter);
        }
        return converted;
    }

    public static <T, E> Set<T> convertSet(Set<E> set, ObjectConverter<T, E> converter) {
        Set<T> converted = null;
        if (set != null) {
            set = new HashSet<>();
            copyAndConvert(set, converted, converter);
        }
        return converted;
    }

    /**
     * Copies and convert all objects from collection {@code src} into collection {@code dest}
     *
     * @param src       collection of original objects
     * @param dest      collectoin to add converted objects
     * @param converter interface to convert objects
     * @param <T>       converted object type
     * @param <E>       original object type
     */
    public static <T, E> void copyAndConvert(Collection<E> src, Collection<T> dest, ObjectConverter<T, E> converter) {
        if (src != null && dest != null && converter != null) {
            for (E obj : src) {
                Extra.ignoreExceptions(() -> dest.add(converter.convert(obj)));
            }
        }
    }

    public interface ObjectConverter<T, E> {
        T convert(E obj);
    }
}
