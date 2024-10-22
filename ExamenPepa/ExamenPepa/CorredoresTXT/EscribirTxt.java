import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirTxt extends Archivo {
    private BufferedWriter escritor;

    public EscribirTxt(String nombreArchivo) {
        super(nombreArchivo);
    }

    @Override
    public void abrirArchivo() throws IOException {
        escritor = new BufferedWriter(new FileWriter(archivo, true)); // 'true' para añadir al final del archivo
    }

    public void escribirLinea(String linea) throws IOException {
        escritor.write(linea);
        escritor.newLine();
    }

    @Override
    public void cerrarArchivo() throws IOException {
        if (escritor != null) {
            escritor.close();
        }
    }
}
