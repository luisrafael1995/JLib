package pt.luisrafael1995.lib.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

public final class GsonUtil {

    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    private GsonUtil() {
    }

    // basic methods - String
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

    // basic methods - File
    public static <T> boolean toJson(T object, File file) {
        try {
            GSON.toJson(object, new FileWriter(file));
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private static <T> T getRawObject(File json, Type type, GetDefault<T> getDefault) {
        try {
            return GSON.fromJson(new FileReader(json), type);
        } catch (Exception ignore) {
        }
        return getDefault == null ? null : getDefault.get();
    }

    private static <T> T getRawObject(File json, Type type) {
        return getRawObject(json, type, null);
    }

    // get with defaults - String
    public static <T> T getObjectDef(String json, TypeToken<T> typeToken, GetDefault<T> getDefValue) {
        return getRawObject(json, typeToken.getType(), getDefValue);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken, T defValue) {
        return getObjectDef(json, typeToken, () -> defValue);
    }

    public static <T> T getObject(String json, TypeToken<T> typeToken) {
        return getObject(json, typeToken, null);
    }

    public static <T> T getObjectDef(String json, Class<T> type, GetDefault<T> getDefValue) {
        return getRawObject(json, type, getDefValue);
    }

    public static <T> T getObject(String json, Class<T> type, T defValue) {
        return getObjectDef(json, type, () -> defValue);
    }

    public static <T> T getObject(String json, Class<T> type) {
        return getObject(json, type, null);
    }

    // get with defaults - File
    public static <T> T getObjectDef(File json, TypeToken<T> typeToken, GetDefault<T> getDefValue) {
        return getRawObject(json, typeToken.getType(), getDefValue);
    }

    public static <T> T getObject(File json, TypeToken<T> typeToken, T defValue) {
        return getObjectDef(json, typeToken, () -> defValue);
    }

    public static <T> T getObject(File json, TypeToken<T> typeToken) {
        return getObject(json, typeToken, null);
    }

    public static <T> T getObjectDef(File json, Class<T> type, GetDefault<T> getDefValue) {
        return getRawObject(json, type, getDefValue);
    }

    public static <T> T getObject(File json, Class<T> type, T defValue) {
        return getObjectDef(json, type, () -> defValue);
    }

    public static <T> T getObject(File json, Class<T> type) {
        return getObject(json, type, null);
    }

    // help methods
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
