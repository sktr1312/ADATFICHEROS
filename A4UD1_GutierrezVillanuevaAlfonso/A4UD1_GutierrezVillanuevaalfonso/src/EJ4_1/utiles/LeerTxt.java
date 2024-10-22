package EJ4_1.utiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerTxt extends Archivo {

    private BufferedReader lector;

    public LeerTxt(String path) {
        super(path);
    }

    @Override
    public void abrirArchivo() {
        try {
            lector = new BufferedReader(new FileReader(this));
        } catch (FileNotFoundException ex) {
        }
    }

    public String leerLinea() throws IOException {
        return lector.readLine();
    }

    public int contarLineas() {
        return (int) lector.lines().count();
    }

    @Override
    public void cerrarArchivo() {
        if (lector != null) {
            try {
                lector.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }

}
