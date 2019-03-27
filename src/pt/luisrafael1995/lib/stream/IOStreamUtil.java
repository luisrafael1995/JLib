package pt.luisrafael1995.lib.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOStreamUtil {

    private static final int BUFFER_SIZE = 1024;

    private IOStreamUtil() {
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        copy(is, os, null);
    }

    public static void copy(InputStream is, OutputStream os, IOStreamCopyListener listener) throws IOException {
        copy(is, os, BUFFER_SIZE, listener);
    }

    public static void copy(InputStream is, OutputStream os, int bufferSize) throws IOException {
        copy(is, os, bufferSize, null);
    }

    public static void copy(InputStream is, OutputStream os, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        if (is == null || os == null) {
            throw new IOException(new NullPointerException(is == null ? "is" : "os"));
        }

        byte[] buffer = new byte[bufferSize];
        long total = 0;
        int read;
        while ((read = is.read(buffer)) > 0) {
            os.write(buffer, 0, read);
            if (listener != null) {
                total += read;
                listener.progress(total);
            }
        }
    }

    public interface IOStreamCopyListener {
        void progress(long bytes);
    }
}
