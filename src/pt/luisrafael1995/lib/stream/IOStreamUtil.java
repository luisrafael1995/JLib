package pt.luisrafael1995.lib.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public final class IOStreamUtil {

    private static final int BUFFER_SIZE = 1024;

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
        return copy(new Readable(input), new Writable(output), bufferSize, listener);
    }

    public static long copy(InputStream input, RandomAccessFile output) throws IOException {
        return copy(input, output, null);
    }

    public static long copy(InputStream input, RandomAccessFile output, IOStreamCopyListener listener) throws IOException {
        return copy(input, output, BUFFER_SIZE, listener);
    }

    public static long copy(InputStream input, RandomAccessFile output, int bufferSize) throws IOException {
        return copy(input, output, bufferSize, null);
    }

    public static long copy(InputStream input, RandomAccessFile output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), new Writable(output), bufferSize, listener);
    }

    public static long copy(RandomAccessFile input, OutputStream output) throws IOException {
        return copy(input, output, null);
    }

    public static long copy(RandomAccessFile input, OutputStream output, IOStreamCopyListener listener) throws IOException {
        return copy(input, output, BUFFER_SIZE, listener);
    }

    public static long copy(RandomAccessFile input, OutputStream output, int bufferSize) throws IOException {
        return copy(input, output, bufferSize, null);
    }

    public static long copy(RandomAccessFile input, OutputStream output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), new Writable(output), bufferSize, listener);
    }

    public static long copy(RandomAccessFile input, RandomAccessFile output) throws IOException {
        return copy(input, output, null);
    }

    public static long copy(RandomAccessFile input, RandomAccessFile output, IOStreamCopyListener listener) throws IOException {
        return copy(input, output, BUFFER_SIZE, listener);
    }

    public static long copy(RandomAccessFile input, RandomAccessFile output, int bufferSize) throws IOException {
        return copy(input, output, bufferSize, null);
    }

    public static long copy(RandomAccessFile input, RandomAccessFile output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), new Writable(output), bufferSize, listener);
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

    public static long copy(InputStream input, int length, OutputStream output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), length, new Writable(output), bufferSize, listener);
    }

    public static long copy(InputStream input, int length, RandomAccessFile output) throws IOException {
        return copy(input, length, output, null);
    }

    public static long copy(InputStream input, int length, RandomAccessFile output, IOStreamCopyListener listener) throws IOException {
        return copy(input, length, output, BUFFER_SIZE, listener);
    }

    public static long copy(InputStream input, int length, RandomAccessFile output, int bufferSize) throws IOException {
        return copy(input, length, output, bufferSize, null);
    }

    public static long copy(InputStream input, int length, RandomAccessFile output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), length, new Writable(output), bufferSize, listener);
    }

    public static long copy(RandomAccessFile input, int length, OutputStream output) throws IOException {
        return copy(input, length, output, null);
    }

    public static long copy(RandomAccessFile input, int length, OutputStream output, IOStreamCopyListener listener) throws IOException {
        return copy(input, length, output, BUFFER_SIZE, listener);
    }

    public static long copy(RandomAccessFile input, int length, OutputStream output, int bufferSize) throws IOException {
        return copy(input, length, output, bufferSize, null);
    }

    public static long copy(RandomAccessFile input, int length, OutputStream output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), length, new Writable(output), bufferSize, listener);
    }

    public static long copy(RandomAccessFile input, int length, RandomAccessFile output) throws IOException {
        return copy(input, length, output, null);
    }

    public static long copy(RandomAccessFile input, int length, RandomAccessFile output, IOStreamCopyListener listener) throws IOException {
        return copy(input, length, output, BUFFER_SIZE, listener);
    }

    public static long copy(RandomAccessFile input, int length, RandomAccessFile output, int bufferSize) throws IOException {
        return copy(input, length, output, bufferSize, null);
    }

    public static long copy(RandomAccessFile input, int length, RandomAccessFile output, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(new Readable(input), length, new Writable(output), bufferSize, listener);
    }


    /**
     * General copy function
     *
     * @param readable   what will be copied (warped input)
     * @param length     maximum amound of bytes to read, negative value means read all bytes
     * @param writable   where it will copy (warped output)
     * @param bufferSize size of the buffer
     * @param listener   callback that give the amount of copied bytes
     * @throws IOException throws exceptions of read and write operations {@see OutputStream, InputStream and
     *                     RandomFileAccess}
     */
    private static long copy(Readable readable, long length, Writable writable, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        if (!readable.isValid()) {
            throw new IOException(new NullPointerException("input"));
        }

        if (!writable.isValid()) {
            throw new IOException(new NullPointerException("output"));
        }

        bufferSize = bufferSize <= 0 ? BUFFER_SIZE : bufferSize;
        boolean realAll = length < 0;

        byte[] buffer = new byte[bufferSize];
        long totalRead = 0;
        while (realAll || totalRead < length) {
            long diff = realAll ? bufferSize : (length - totalRead);
            int newBufferSize = diff < bufferSize ? (int) diff : bufferSize;
            int read = realAll ? readable.read(buffer) : readable.read(buffer, 0, newBufferSize);
            if (read >= 0) {
                writable.write(buffer, 0, read);
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

    private static long copy(Readable readable, Writable writable, int bufferSize, IOStreamCopyListener listener)
            throws IOException {
        return copy(readable, -1, writable, bufferSize, listener);
    }


    public interface IOStreamCopyListener {
        void copied(long bytes);
    }

    /**
     * Class that warps writable classes into one
     */
    private static final class Writable {

        private final OutputStream outputStream;
        private final RandomAccessFile randomAccessFile;

        private Writable(RandomAccessFile randomAccessFile) {
            this.randomAccessFile = randomAccessFile;
            this.outputStream = null;
        }

        private Writable(OutputStream outputStream) {
            this.outputStream = outputStream;
            this.randomAccessFile = null;
        }

        private boolean isValid() {
            return outputStream != null || randomAccessFile != null;
        }

        private void write(int b) throws IOException {
            if (outputStream != null) {
                outputStream.write(b);
            } else if (randomAccessFile != null) {
                randomAccessFile.write(b);
            }
        }

        private void write(byte[] b) throws IOException {
            if (outputStream != null) {
                outputStream.write(b);
            } else if (randomAccessFile != null) {
                randomAccessFile.write(b);
            }
        }

        private void write(byte[] b, int off, int len) throws IOException {
            if (outputStream != null) {
                outputStream.write(b, off, len);
            } else if (randomAccessFile != null) {
                randomAccessFile.write(b, off, len);
            }
        }

    }

    /**
     * Class that warps readable classes into one
     */
    private static final class Readable {

        private final InputStream inputStream;
        private final RandomAccessFile randomAccessFile;

        private Readable(RandomAccessFile randomAccessFile) {
            this.randomAccessFile = randomAccessFile;
            this.inputStream = null;
        }

        private Readable(InputStream inputStream) {
            this.inputStream = inputStream;
            this.randomAccessFile = null;
        }

        private boolean isValid() {
            return inputStream != null || randomAccessFile != null;
        }

        private int read() throws IOException {
            if (inputStream != null) {
                return inputStream.read();
            } else if (randomAccessFile != null) {
                return randomAccessFile.read();
            }
            return -1;
        }

        private int read(byte[] b) throws IOException {
            if (inputStream != null) {
                return inputStream.read(b);
            } else if (randomAccessFile != null) {
                return randomAccessFile.read(b);
            }
            return -1;
        }

        private int read(byte[] b, int off, int len) throws IOException {
            if (inputStream != null) {
                return inputStream.read(b, off, len);
            } else if (randomAccessFile != null) {
                return randomAccessFile.read(b, off, len);
            }
            return -1;
        }

    }
}
