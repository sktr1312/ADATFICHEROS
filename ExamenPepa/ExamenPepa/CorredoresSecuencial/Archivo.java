import java.io.File;

public abstract class Archivo {
    protected File f;

    public Archivo(String nombreArchivo) {
        this.f = new File(nombreArchivo);
    }

    public boolean existeArchivo() {
        return f.exists();
    }

    public abstract void abrirArchivo() throws Exception;

    public abstract void cerrarArchivo() throws Exception;
}
