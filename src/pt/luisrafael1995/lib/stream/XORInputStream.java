package pt.luisrafael1995.lib.stream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XORInputStream extends FilterInputStream {

    private final int xor;

    public XORInputStream(InputStream in, int xor) {
        super(in);
        this.xor = xor;
    }

    public XORInputStream(InputStream in) {
        this(in, 0x0ff);
    }

    @Override
    public int read() throws IOException {
        return super.read() ^ xor;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read = super.read(b, off, len);
        xor(b, off, len);
        return read;
    }

    private void xor(byte[] b, int off, int len) {
        for (int i = 0; i < len; i++) {
            b[off + i] ^= xor;
        }
    }
}
