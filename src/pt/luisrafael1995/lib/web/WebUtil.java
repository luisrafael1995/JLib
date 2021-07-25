package pt.luisrafael1995.lib.web;

import okhttp3.*;
import pt.luisrafael1995.lib.gson.GsonUtil;
import pt.luisrafael1995.lib.stream.IOStreamUtil;
import pt.luisrafael1995.lib.text.StringUtil;
import pt.luisrafael1995.lib.util.Wrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public final class WebUtil {

    private static final int CONNECT_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
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
        Wrapper<byte[]> response = new Wrapper<>();
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
