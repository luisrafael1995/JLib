package pt.luisrafael1995.lib.stream;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XOROutputStream extends FilterOutputStream {

    private final int xor;

    public XOROutputStream(OutputStream out, int xor) {
        super(out);
        this.xor = xor;
    }

    public XOROutputStream(OutputStream out) {
        this(out, 0x0ff);
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b ^ xor);
    }
}
