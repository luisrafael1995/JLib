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

    private static <T> T getRawObjectDef(String json, Type type, GetDefault<T> getDefValue) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception ignore) {
        }
        return getDefValue == null ? null : getDefValue.get();
    }

    private static <T> T getRawObject(String json, Type type, T defValue) {
        return getRawObjectDef(json, type, () -> defValue);
    }

    private static <T> T getRawObject(String json, Type type) {
        return getRawObject(json, type, null);
    }

    public static <T> T getObjectDef(String json, TypeToken<T> typeToken, GetDefault<T> getDefValue) {
        return getRawObjectDef(json, typeToken.getType(), getDefValue);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken, T defValue) {
        return getRawObject(json, typeToken.getType(), defValue);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken) {
        return getRawObject(json, typeToken.getType());
    }

    public static <T> T getObjectDef(String json, Class<T> type, GetDefault<T> getDefValue) {
        return getRawObjectDef(json, type, getDefValue);
    }

    public static <T> T getObject(String json, Class<T> type, T defValue) {
        return getRawObject(json, type, defValue);
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
