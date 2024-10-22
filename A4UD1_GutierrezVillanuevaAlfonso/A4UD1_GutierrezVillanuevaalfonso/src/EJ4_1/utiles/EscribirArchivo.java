package EJ4_1.utiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Predicate;

public class EscribirArchivo extends Archivo {

    ObjectOutputStream out;

    public EscribirArchivo(String path) {
        super(path);
    }

    public <T extends Serializable> void anhadirObjeto(T objeto) {
        try {

            out.writeObject(objeto);
            out.flush();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo " + e.getMessage());
        }
    }

    @Override
    public void abrirArchivo() {
        if (!this.exists()) {
            try {
                out = new ObjectOutputStream(new FileOutputStream(this));
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al abrir el archivo " + e.getMessage());
            }
        } else {
            try {
                out = new MiObjectOutputStream(new FileOutputStream(this, true));
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo " + e.getMessage());
            }

        }

    }

    @Override
    public void cerrarArchivo() {
        try {
            out.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al cerrar el archivo " + e.getMessage());
        }
    }

    public <T extends Serializable> boolean modificarRegistro(Predicate<T> condicion, T objeto) {
        boolean modificado = false;
        boolean sw = false;
        if (new File(this.getPath()).exists()) {
            LeerArchivo leerArchivo = new LeerArchivo(this.getPath());
            EscribirArchivo escribirArchivo = new EscribirArchivo(this.getPath() + ".tmp");
            leerArchivo.abrirArchivo();
            escribirArchivo.abrirArchivo();
            while (!sw) {
                try {
                    T registro = (T) leerArchivo.leerRegistro();
                    if (condicion.test((T) registro)) {
                        escribirArchivo.anhadirObjeto(objeto);
                        modificado = true;
                    } else {
                        escribirArchivo.anhadirObjeto(registro);
                    }
                } catch (IOException e) {
                    sw = true;
                }
            }

            leerArchivo.cerrarArchivo();
            escribirArchivo.cerrarArchivo();
            if (modificado && (!leerArchivo.delete() || !escribirArchivo.renameTo(new File(this.getPath())))) {
                escribirArchivo.delete();
                modificado = false;
            }

        }
        return modificado;

    }

    public <T extends Serializable> boolean eliminarRegistro(Predicate<T> condicion) {
        boolean eliminado = false;
        boolean sw = false;
        if (this.exists()) {
            LeerArchivo leerArchivo = new LeerArchivo(this.getPath());
            EscribirArchivo escribirArchivo = new EscribirArchivo(this.getPath() + ".tmp");
            leerArchivo.abrirArchivo();
            escribirArchivo.abrirArchivo();
            while (!sw) {
                try {
                    T registro = (T) leerArchivo.leerRegistro();
                    if (condicion.test((T) registro)) {
                        eliminado = true;
                    } else {
                        escribirArchivo.anhadirObjeto(registro);
                    }
                } catch (IOException e) {
                    sw = true;
                }
            }

            leerArchivo.cerrarArchivo();
            escribirArchivo.cerrarArchivo();
            if (eliminado && (!leerArchivo.delete() || !escribirArchivo.renameTo(new File(this.getPath())))) {
                escribirArchivo.delete();
                eliminado = false;
            }

        }
        return eliminado;

    }

}
