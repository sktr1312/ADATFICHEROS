package EJ4_1.utiles;

import java.io.File;

public abstract class Archivo extends File {
    

    public Archivo(String pathname) {
        super(pathname);
    }

    public abstract void abrirArchivo();

    public abstract void cerrarArchivo();

}
