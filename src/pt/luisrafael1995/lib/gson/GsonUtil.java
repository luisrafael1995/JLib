package pt.luisrafael1995.lib.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public final class GsonUtil {

    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    private GsonUtil() {
    }

    public static <T> String toJson(T object) {
        try {
            return GSON.toJson(object);
        } catch (Exception ignore) {
        }
        return null;
    }

    private static <T> T getRawObject(String json, Type type, GetDefault<T> getDefault) {
        try {
            return GSON.fromJson(json, type);
        } catch (Exception ignore) {
        }
        return getDefault == null ? null : getDefault.get();
    }

    private static <T> T getRawObject(String json, Type type) {
        return getRawObject(json, type, null);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken) {
        return getRawObject(json, typeToken.getType());
    }

    public static <T> T getObject(String json, Class<T> type, GetDefault<T> getDefValue) {
        return getRawObject(json, type, getDefValue);
    }

    public static <T> T getObject(String json, Class<T> type) {
        return getRawObject(json, type);
    }

    public static <T> T clone(T object) {
        return object == null ? null : getRawObject(toJson(object), object.getClass());
    }

    public static <T, E> E cast(T object, Class<E> c) {
        return getObject(toJson(object), c);
    }

    public interface GetDefault<T> {
        T get();
    }
}
