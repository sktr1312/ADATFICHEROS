import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerTxt extends Archivo {
    private BufferedReader lector;

    public LeerTxt(String nombreArchivo) {
        super(nombreArchivo);
    }

    @Override
    public void abrirArchivo() throws IOException {
        lector = new BufferedReader(new FileReader(archivo));
    }

    public String leerLinea() throws IOException {
        return lector.readLine();
    }

    @Override
    public void cerrarArchivo() throws IOException {
        if (lector != null) {
            lector.close();
        }
    }
}
