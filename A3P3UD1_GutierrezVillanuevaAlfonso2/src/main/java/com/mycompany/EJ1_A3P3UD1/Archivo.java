package com.mycompany.EJ1_A3P3UD1;

import java.io.File;

public abstract class Archivo extends File {
    

    public Archivo(String pathname) {
        super(pathname);
    }

    public abstract void abrirArchivo();

    public abstract void cerrarArchivo();

}
