import java.io.File;

public abstract class Archivo {
    protected File archivo;

    public Archivo(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }

    public boolean existeArchivo() {
        return archivo.exists();
    }

    public abstract void abrirArchivo() throws Exception;

    public abstract void cerrarArchivo() throws Exception;
}
