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
        } catch (JsonSyntaxException e) {
            //e.printStackTrace();
            return null;
        }
    }
}
