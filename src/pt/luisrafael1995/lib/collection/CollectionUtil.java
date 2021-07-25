package pt.luisrafael1995.lib.collection;

import pt.luisrafael1995.lib.random.RandomPlus;
import pt.luisrafael1995.lib.util.Extra;

import java.util.*;

public final class CollectionUtil {

    private CollectionUtil() {
    }

    private static <E> E getRandom(List<E> list) {
        if (list != null && list.size() != 0) {
            int index = RandomPlus.getInstance().nextInt(list.size());
            return list.get(index);
        }
        return null;
    }

    public static <E> E getRandom(Collection<E> collection) {
        List<E> list = null;
        if (collection != null) {
            boolean cast = collection instanceof List;
            list = cast ? (List<E>) collection : new ArrayList<>(collection);
        }
        return getRandom(list);
    }

    public static <E> E getLast(List<E> list) {
        if (list != null && list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public static <E> E getFirst(List<E> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static <E> boolean addUnique(Collection<E> collection, E object) {
        if (collection != null && !collection.contains(object)) {
            collection.add(object);
            return true;
        }
        return false;
    }

    public static <E> boolean addUnique(Collection<E> collection, Collection<E> toAdd) {
        boolean added = false;
        for (E object : toAdd) {
            added |= addUnique(collection, object);
        }
        return added;
    }

    public static <E> boolean containsAllEach(Collection<E> collection1, Collection<E> collection2) {
        return collection1 != null && collection2 != null && collection1.containsAll(collection2) &&
                collection2.containsAll(collection1);
    }

    /**
     * This method will do the same as {@link Map#getOrDefault} except that it will also set the {@code defaultValue}
     * in case the value is not set yet
     *
     * @param map          map to get and/or set the value
     * @param key          key of the value
     * @param defaultValue generates value to get as default and also set
     * @param <K>          key object type
     * @param <V>          value object type
     * @return the same as {@link Map#getOrDefault}
     */
    public static <K, V> V getOrDefaultSet(Map<K, V> map, K key, DefaultValue<V> defaultValue) {
        if (defaultValue == null) {
            defaultValue = () -> null;
        }

        if (map != null && map.containsKey(key)) {
            return map.get(key);
        }

        V value = defaultValue.get();
        if (map != null) {
            map.put(key, value);
        }

        return value;
    }

    public static <E> boolean hasDuplicatedValues(List<E> list) {
        if (list != null) {
            for (E e : list) {
                int firstIndex = list.indexOf(e);
                int lastIndex = list.lastIndexOf(e);
                if (firstIndex != lastIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <E> void removeDuplicatedValues(List<E> list) {
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; i--) {
                E e = list.get(i);
                int index = list.indexOf(e);
                if (index != i) {
                    list.remove(i);
                }
            }
        }
    }

    public static <T, E> List<T> convertList(Collection<E> collection, ObjectConverter<T, E> converter) {
        List<T> converted = null;
        if (collection != null) {
            converted = new ArrayList<>();
            convert(collection, converted, converter);
        }
        return converted;
    }

    public static <T, E> Set<T> convertSet(Collection<E> collection, ObjectConverter<T, E> converter) {
        Set<T> converted = null;
        if (collection != null) {
            converted = new HashSet<>();
            convert(collection, converted, converter);
        }
        return converted;
    }

    /**
     * Converts all objects from src {@code src} into dst {@code dst}
     *
     * @param src       src of original objects
     * @param dst       dst to add converted objects
     * @param converter interface to convert objects
     * @param <T>       converted object type
     * @param <E>       original object type
     */
    public static <T, E> void convert(Collection<E> src, Collection<T> dst, ObjectConverter<T, E> converter) {
        if (src != null && dst != null && converter != null) {
            for (E obj : src) {
                Extra.ignoreExceptions(() -> {
                    T converted = converter.convert(obj);
                    dst.add(converted);
                });
            }
        }
    }

    public static <T, E> List<T> convertListFlat(Collection<E> collection, ObjectConverterFlat<T, E> converter) {
        List<T> converted = null;
        if (collection != null) {
            converted = new ArrayList<>();
            convertFlat(collection, converted, converter);
        }
        return converted;
    }

    public static <T, E> Set<T> convertSetFlat(Collection<E> collection, ObjectConverterFlat<T, E> converter) {
        Set<T> converted = null;
        if (collection != null) {
            converted = new HashSet<>();
            convertFlat(collection, converted, converter);
        }
        return converted;
    }

    /**
     * Converts and flats a dst of objects from src {@code src} into dst {@code dst}
     *
     * @param src       src of original objects
     * @param dst       dst to add converted objects
     * @param converter interface to convert objects
     * @param <T>       converted object type
     * @param <E>       original object type
     */
    public static <T, E> void convertFlat(Collection<E> src, Collection<T> dst, ObjectConverterFlat<T, E> converter) {
        if (src != null && dst != null && converter != null) {
            for (E obj : src) {
                Extra.ignoreExceptions(() -> {
                    Collection<T> converted = converter.convert(obj);
                    dst.addAll(converted);
                });
            }
        }
    }

    public static <E> boolean has(Collection<E> collection, Condition<E> condition) {
        if (collection != null && condition != null) {
            for (E obj : collection) {
                if (Extra.ignoreExceptions(() -> condition.hasCondition(obj))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <E> E find(Collection<E> collection, Condition<E> condition) {
        if (collection != null && condition != null) {
            for (E obj : collection) {
                if (Extra.ignoreExceptions(() -> condition.hasCondition(obj))) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static <E> void filter(Collection<E> collection, Condition<E> condition) {
        if (collection != null && condition != null) {
            List<E> copy = new ArrayList<>(collection);
            collection.clear();
            filter(copy, collection, condition);
        }
    }

    public static <E> List<E> filterList(Collection<E> collection, Condition<E> condition) {
        List<E> filteredList = new ArrayList<>();
        filter(collection, filteredList, condition);
        return filteredList;
    }

    public static <E> Set<E> filterSet(Collection<E> collection, Condition<E> condition) {
        Set<E> filteredSet = new HashSet<>();
        filter(collection, filteredSet, condition);
        return filteredSet;
    }

    public static <E> void filter(Collection<E> src, Collection<E> dst, Condition<E> condition) {
        if (src != null && dst != null && condition != null) {
            for (E obj : src) {
                if (Extra.ignoreExceptions(() -> condition.hasCondition(obj))) {
                    dst.add(obj);
                }
            }
        }
    }

    public static <E> int count(Collection<E> collection, Condition<E> condition) {
        int count = 0;
        if (collection != null && condition != null) {
            for (E obj : collection) {
                if (Extra.ignoreExceptions(() -> condition.hasCondition(obj))) {
                    count++;
                }
            }
        }
        return count;
    }


    public interface DefaultValue<E> {
        E get();
    }

    public interface ObjectConverter<T, E> {
        T convert(E obj);
    }

    public interface ObjectConverterFlat<T, E> {
        Collection<T> convert(E obj);
    }

    public interface Condition<E> {
        boolean hasCondition(E obj);
    }
}
