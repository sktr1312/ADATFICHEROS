package EJ4_1.utiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirTxt extends Archivo {

    BufferedWriter escritor;

    public EscribirTxt(String path) {
        super(path);
    }

    @Override
    public void abrirArchivo() {
        try {
            escritor = new BufferedWriter(new FileWriter(this));

        } catch (IOException e) {
            System.out.println("Error al importar el archivo");
        }
    }

    public void escribirCabecera(String cabecera) throws IOException {
        escribirLinea(cabecera);
    }

    public void escribirLinea(String linea) throws IOException {
        escritor.write(linea);
        escritor.newLine();
    }

    public void escribirSinSalto(String linea) throws IOException {
        escritor.write(linea);
    }

    @Override
    public void cerrarArchivo() {
        if (escritor != null) {
            try {
                escritor.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }

    public <T> void añadirRegistro(T registro) throws IOException {
        escribirLinea(registro.toString());
    }

}
