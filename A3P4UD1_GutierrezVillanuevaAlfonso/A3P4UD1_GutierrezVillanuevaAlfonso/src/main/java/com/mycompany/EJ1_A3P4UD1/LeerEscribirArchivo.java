package EJ1_A3P4UD1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerEscribirArchivo extends Archivo {

    final private int TAM_REGISTRO = 150;
    RandomAccessFile raf;

    public LeerEscribirArchivo(String pathname) {
        super(pathname);

    }

    @Override
    void abrirArchivo() {
        try {
            raf = new RandomAccessFile(this, "rw");
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo");
        }

    }

    @Override
    void cerrarArchivo() {
        try {
            raf.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo");
        }
    }

    public void escribirRegistro(int numRegistro, String nombre, double tiempo, boolean borrado) {
        try {
            raf.seek((numRegistro - 1) * TAM_REGISTRO);
            raf.writeInt(numRegistro);
            raf.writeDouble(tiempo);
            raf.writeBoolean(borrado);
            raf.writeUTF(nombre);
        } catch (IOException e) {
            System.out.println("Error al escribir el registro " + e.getMessage());
        }
    }

    public Corredor recuperarRegistro(int numRegistro) {

        Corredor corredor = new Corredor();
        try {
            raf.seek((numRegistro - 1) * TAM_REGISTRO);
            corredor.setDorsal(raf.readInt());
            corredor.setTiempo(raf.readDouble());
            corredor.setBorrado(raf.readBoolean());
            corredor.setNombre(raf.readUTF().trim());

        } catch (IOException e) {
            System.out.println("Error al leer el registro");
        }
        return corredor;
    }

    public int numRegistros() {
        int numRegistros;
        try {
            numRegistros = (int) Math.ceil((double) raf.length() / TAM_REGISTRO);
        } catch (IOException e) {
            numRegistros = 0;
        }
        return numRegistros;
    }

    public void crearArchivo() {
        abrirArchivo();
        cerrarArchivo();
    }

}
