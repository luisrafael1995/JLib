package pt.luisrafael1995.lib.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pt.luisrafael1995.lib.gson.GsonUtil;
import pt.luisrafael1995.lib.stream.IOStreamUtil;
import pt.luisrafael1995.lib.text.StringUtil;
import pt.luisrafael1995.lib.util.ObjectWrapper;

public final class WebUtil {

    private static final String TAG = "WebUtil";

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(7, TimeUnit.SECONDS)
            .writeTimeout(7, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followSslRedirects(true)
            .followRedirects(true)
            .build();

    private WebUtil() {
    }

    //
    // Request Builder
    //
    private static Request.Builder getRequestBuilder(String url, Headers headers) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            builder.headers(headers);
        }
        return builder;
    }

    //
    // Request
    //
    public static Request newGetRequest(String url) {
        return newGetRequest(url, null);
    }

    public static Request newGetRequest(String url, Headers headers) {
        return getRequestBuilder(url, headers).get().build();
    }

    public static Request newPutRequest(String url, MediaType mediaType, File content) {
        return newPutRequest(url, null, mediaType, content);
    }

    public static Request newPutRequest(String url, Headers headers, MediaType mediaType, File content) {
        return getRequestBuilder(url, headers).put(RequestBody.create(mediaType, content)).build();
    }

    public static Request newPutRequest(String url, MediaType mediaType, String content) {
        return newPutRequest(url, null, mediaType, content);
    }

    public static Request newPutRequest(String url, Headers headers, MediaType mediaType, String content) {
        return getRequestBuilder(url, headers).put(RequestBody.create(mediaType, content)).build();
    }

    public static Request newPutRequest(String url, MediaType mediaType, byte[] content) {
        return newPutRequest(url, null, mediaType, content);
    }

    public static Request newPutRequest(String url, Headers headers, MediaType mediaType, byte[] content) {
        return getRequestBuilder(url, headers).put(RequestBody.create(mediaType, content)).build();
    }

    public static Request newPostRequest(String url, MediaType mediaType, File content) {
        return newPostRequest(url, null, mediaType, content);
    }

    public static Request newPostRequest(String url, Headers headers, MediaType mediaType, File content) {
        return getRequestBuilder(url, headers).post(RequestBody.create(mediaType, content)).build();
    }

    public static Request newPostRequest(String url, MediaType mediaType, String content) {
        return newPostRequest(url, null, mediaType, content);
    }

    public static Request newPostRequest(String url, Headers headers, MediaType mediaType, String content) {
        return getRequestBuilder(url, headers).post(RequestBody.create(mediaType, content)).build();
    }

    public static Request newPostRequest(String url, MediaType mediaType, byte[] content) {
        return newPostRequest(url, null, mediaType, content);
    }

    public static Request newPostRequest(String url, Headers headers, MediaType mediaType, byte[] content) {
        return getRequestBuilder(url, headers).post(RequestBody.create(mediaType, content)).build();
    }

    public static Request newDeleteRequest(String url) {
        return newDeleteRequest(url, null);
    }

    public static Request newDeleteRequest(String url, Headers headers) {
        return getRequestBuilder(url, headers).delete().build();
    }

    public static Request newDeleteRequest(String url, MediaType mediaType, File content) {
        return newDeleteRequest(url, null, mediaType, content);
    }

    public static Request newDeleteRequest(String url, Headers headers, MediaType mediaType, File content) {
        return getRequestBuilder(url, headers).delete(RequestBody.create(mediaType, content)).build();
    }

    public static Request newDeleteRequest(String url, MediaType mediaType, String content) {
        return newDeleteRequest(url, null, mediaType, content);
    }

    public static Request newDeleteRequest(String url, Headers headers, MediaType mediaType, String content) {
        return getRequestBuilder(url, headers).delete(RequestBody.create(mediaType, content)).build();
    }

    public static Request newDeleteRequest(String url, MediaType mediaType, byte[] content) {
        return newDeleteRequest(url, null, mediaType, content);
    }

    public static Request newDeleteRequest(String url, Headers headers, MediaType mediaType, byte[] content) {
        return getRequestBuilder(url, headers).delete(RequestBody.create(mediaType, content)).build();
    }

    //
    // Response
    //
    public static Call newCall(Request request) {
        return HTTP_CLIENT.newCall(request);
    }

    public static boolean call(Request request) {
        try (Response response = newCall(request).execute()) {
            return response.isSuccessful();
        } catch (IOException ignore) {
        }
        return false;
    }

    public static void callbackStream(Request request, StreamCallback callback) {
        try (Response response = newCall(request).execute(); ResponseBody body = response.body()) {
            if (response.isSuccessful() && body != null) {
                try (InputStream is = body.byteStream()) {
                    callback.call(is);
                }
            }
        } catch (IOException ignore) {
        }
    }

    public static byte[] getResponse(Request request) {
        ObjectWrapper<byte[]> response = new ObjectWrapper<>();
        callbackStream(request, is -> {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                IOStreamUtil.copy(is, baos);
                response.set(baos.toByteArray());
            }
        });
        return response.get();

//        try (Response response = newCall(request).execute(); ResponseBody body = response.body()) {
//            if (response.isSuccessful() && body != null) {
//                return body.bytes();
//            } else {
//                AppLog.e(TAG, "response is not successful");
//            }
//        } catch (IOException e) {
//            AppLog.e(TAG, "unable to get response", e);
//        }
//        return null;
    }

    public static String getResponseString(Request request) {
        return StringUtil.toString(getResponse(request));
    }

    public static <T> T getResponseObject(Request request, Class<T> cClass) {
        return GsonUtil.getObject(getResponseString(request), cClass);
    }

    public static void callback(Request request, SuccessResponseCallback callback) {
        try (Response response = newCall(request).execute()) {
            callback.onSuccess(request, response);
        } catch (IOException exception) {
            if (callback instanceof ResponseCallback) {
                ((ResponseCallback) callback).onFailure(request, exception);
            }
        }
    }

    public interface SuccessResponseCallback {
        void onSuccess(Request request, Response response);
    }

    public interface ResponseCallback extends SuccessResponseCallback {
        void onFailure(Request request, IOException exception);
    }

    public interface StreamCallback {
        void call(InputStream is) throws IOException;
    }
}
