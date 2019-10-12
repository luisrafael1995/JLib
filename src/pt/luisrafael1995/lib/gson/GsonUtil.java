package pt.luisrafael1995.lib.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public final class GsonUtil {

    private static final Gson gson = new Gson();

    private GsonUtil() {
    }

    public static <T> String toJson(T object) {
        try {
            return gson.toJson(object);
        } catch (Exception ignore) {
        }
        return null;
    }

    private static <T> T getObject(String json, Type type, GetDefault<T> getDefault) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception ignore) {
        }
        return getDefault == null ? null : getDefault.get();
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken, GetDefault<T> getDefault) {
        return getObject(json, typeToken.getType(), getDefault);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken, T orDefault) {
        return getObject(json, typeToken.getType(), () -> orDefault);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken) {
        return getObject(json, typeToken.getType(), null);
    }

    public static <T> T getObject(String json, Class<T> type, GetDefault<T> getDefault) {
        return getObject(json, TypeToken.get(type), getDefault);
    }

    public static <T> T getObject(String json, Class<T> type, T orDefault) {
        return getObject(json, TypeToken.get(type), orDefault);
    }

    public static <T> T getObject(String json, Class<T> type) {
        return getObject(json, TypeToken.get(type));
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T object) {
        return object == null ? null : cast(object, (Class<T>) object.getClass());
    }

    public static <T, E> E cast(T object, Class<E> c) {
        return getObject(toJson(object), c);
    }

    public interface GetDefault<T> {
        T get();
    }
}
