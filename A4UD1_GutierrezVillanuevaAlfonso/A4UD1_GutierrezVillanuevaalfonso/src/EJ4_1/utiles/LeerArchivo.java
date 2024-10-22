package EJ4_1.utiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.function.Predicate;

public class LeerArchivo extends Archivo {

    ObjectInputStream in;

    public LeerArchivo(String path) {
        super(path);
    }

    @Override
    public void cerrarArchivo() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al cerrar el archivo " + e.getMessage());
        }
    }

    @Override
    public void abrirArchivo() {
        try {
            in = new ObjectInputStream(new FileInputStream(this));
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo " + e.getMessage());
        }
    }

    public <T extends Serializable> T leerRegistro() throws IOException {
        T objeto = null;
        try {
            objeto = (T) in.readObject();
            return objeto;
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }
        return objeto;
    }

    public <T> T recuperaRegistro(Predicate<T> condicion) throws IOException {
        T registro = null;
        boolean sw = false;

        if (this.exists()) {
            this.abrirArchivo();
            while (!sw) {
                try {
                    registro = (T) this.leerRegistro();
                    if (condicion.test(registro)) {
                        sw = true;
                    }
                } catch (IOException e) {
                    sw = true;
                }
            }
            this.cerrarArchivo();
        } else {
            System.out.println("No existe el archivo");
        }

        return registro;
    }
}
