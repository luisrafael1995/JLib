package pt.luisrafael1995.lib.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOStreamUtil {

    private static final int BUFFER_SIZE = 8192;

    private IOStreamUtil() {
    }

    // copy() without length limit
    public static long copy(InputStream input, OutputStream output) throws IOException {
        return copy(input, output, null);
    }

    public static long copy(InputStream input, OutputStream output, IOStreamCopyListener listener) throws IOException {
        return copy(input, output, BUFFER_SIZE, listener);
    }

    public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        return copy(input, output, bufferSize, null);
    }

    public static long copy(InputStream input, OutputStream output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(input, -1, output, bufferSize, listener);
    }

    // copy() with length limit
    public static long copy(InputStream input, int length, OutputStream output) throws IOException {
        return copy(input, length, output, null);
    }

    public static long copy(InputStream input, int length, OutputStream output, IOStreamCopyListener listener) throws IOException {
        return copy(input, length, output, BUFFER_SIZE, listener);
    }

    public static long copy(InputStream input, int length, OutputStream output, int bufferSize) throws IOException {
        return copy(input, length, output, bufferSize, null);
    }

    /**
     * General copy function
     *
     * @param input      what will be copied
     * @param length     maximum amound of bytes to read, negative value means read all bytes
     * @param output     where it will copy
     * @param bufferSize size of the buffer
     * @param listener   callback that give the amount of copied bytes
     * @throws IOException throws exceptions of read and write operations {@see OutputStream and InputStream}
     */
    public static long copy(InputStream input, int length, OutputStream output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        if (input == null) {
            throw new IOException(new NullPointerException("input"));
        }

        if (output == null) {
            throw new IOException(new NullPointerException("output"));
        }

        bufferSize = bufferSize <= 0 ? BUFFER_SIZE : bufferSize;
        boolean readAll = length < 0;

        byte[] buffer = new byte[bufferSize];
        long totalRead = 0;
        while (readAll || totalRead < length) {
            long diff = readAll ? bufferSize : (length - totalRead);
            int newBufferSize = diff < bufferSize ? (int) diff : bufferSize;
            int read = readAll ? input.read(buffer) : input.read(buffer, 0, newBufferSize);
            if (read >= 0) {
                output.write(buffer, 0, read);
                totalRead += read;
                if (listener != null) {
                    listener.copied(totalRead);
                }
            } else {
                break;
            }
        }

        return totalRead;
    }

    public interface IOStreamCopyListener {
        void copied(long bytes);
    }
}
