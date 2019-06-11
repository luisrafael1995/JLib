package pt.luisrafael1995.lib.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class GsonUtil {

    private static final Gson gson = new Gson();

    private GsonUtil() {
    }

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public static <T> T getObject(String json, Class<T> c) {
        try {
            return gson.fromJson(json, c);
        } catch (JsonSyntaxException ignore) {
            return null;
        }
    }

    public static <T> T getObject(String json, Class<T> c, T orDefault) {
        T object = getObject(json, c);
        return object == null ? orDefault : object;
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T object) {
        return object == null ? null : cast(object, (Class<T>) object.getClass());
    }

    public static <T, E> E cast(T object, Class<E> c) {
        return getObject(toJson(object), c);
    }
}
