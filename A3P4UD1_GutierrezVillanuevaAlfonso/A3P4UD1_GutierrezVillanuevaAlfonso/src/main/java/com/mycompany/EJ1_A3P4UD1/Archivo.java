package EJ1_A3P4UD1;

import java.io.File;

abstract class Archivo extends File {

    public Archivo(String pathname) {
        super(pathname);
    }

    abstract void abrirArchivo();

    abstract void cerrarArchivo();

}
