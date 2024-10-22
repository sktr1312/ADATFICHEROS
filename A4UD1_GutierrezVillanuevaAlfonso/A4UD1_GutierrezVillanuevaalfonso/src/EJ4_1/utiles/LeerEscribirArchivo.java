package EJ4_1.utiles;

import EJ4_1.main.Alumno;
import EJ4_1.main.Nombre;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LeerEscribirArchivo extends Archivo {

    final private int TAM_REGISTRO = 100;
    RandomAccessFile raf;

    public LeerEscribirArchivo(String pathname) {
        super(pathname);

    }

    @Override
    public void abrirArchivo() {
        try {
            raf = new RandomAccessFile(this, "rw");
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo");
        }

    }

    @Override
    public void cerrarArchivo() {
        try {
            raf.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo");
        }
    }

    //private int numero;
    // private Nombre nombre;
    // Date fechaNac;
    // ArrayList<String> telefono;
    // boolean borrado;
    public void escribirRegistro(int numRegistro, String nombre, Long fechNac, int numTelefonos, ArrayList<String> telefono, boolean borrado) {
        try {
            raf.seek((numRegistro - 1) * TAM_REGISTRO);
            raf.writeInt(numRegistro);
            raf.writeLong(fechNac);
            raf.writeInt(numTelefonos);
            raf.writeBoolean(borrado);
            raf.writeUTF(nombre);
            for (int i = 0; i < numTelefonos; i++) {
                raf.writeUTF(telefono.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el registro " + e.getMessage());
        }
    }

    public Alumno recuperarRegistro(int numRegistro) {

        Alumno alumno = new Alumno();
        try {
            raf.seek((numRegistro - 1) * TAM_REGISTRO);
            alumno.setNumero(raf.readInt());
            alumno.setFechaNac(raf.readLong());
            int numTelefonos = raf.readInt();
            alumno.setBorrado(raf.readBoolean());
            String strNombreCompleto = raf.readUTF();
            String strNombre = strNombreCompleto.split(" ")[0];
            String strApellido1 = strNombreCompleto.split(" ")[1];
            String strApellido2 = strNombreCompleto.split(" ")[2];
            Nombre nombre = new Nombre(strNombre, strApellido1, strApellido2);
            alumno.setNombre(nombre);
            for (int i = 0; i < numTelefonos; i++) {
                alumno.getTelefono().add(raf.readUTF());
            }

        } catch (IOException e) {
            System.out.println("Error al leer el registro");
            alumno = null;
        }
        return alumno;
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
