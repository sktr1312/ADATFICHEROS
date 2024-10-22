package EJ4_1.utiles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Do not write a header
    }

}
