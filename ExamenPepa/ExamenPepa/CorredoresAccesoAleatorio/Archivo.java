/*@Author : Borja Otero Ferreiera  */
package ud1a3_p4;
import java.io.File;

public abstract class Archivo {
    File f;

    public Archivo(String f) {
        this.f = new File(f);
    }

    abstract void abrirArchivo();

    abstract void cerrarArchivo();

    public int longitudArchivo() {
        return (int) f.length();
    }

}
