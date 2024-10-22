/*@Author : Borja Otero Ferreiera  */
package ud1a3_p4;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerEscribirAleatorio extends Archivo {
    private RandomAccessFile raf;
    private final int TAM_REGISTRO = 150;
    private boolean primeraVez = true;

    public LeerEscribirAleatorio(String f) {
        super(f);
    }

   @Override
    void abrirArchivo() {
        try {
            if (!f.exists()) {
                // Si el archivo no existe, se crea y se muestra un mensaje
                raf = new RandomAccessFile(f, "rw");
                System.out.println("El archivo no existía, ha sido creado.");
            } else {
                // Si el archivo existe, se abre y se muestra un mensaje con el número de registros
                raf = new RandomAccessFile(f, "rw");
                if(primeraVez) {
                    System.out.println("El archivo ya existe.");
                    System.out.println("Número de dorsales grabados: " + numReg());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        finally {
            primeraVez = false;
        }       
    }

    @Override
    void cerrarArchivo() {
        try {
            raf.close();
        } catch (IOException e) {
            System.out.println("Error de entrada o salida.");
        }
    }
    
    public void mostrarCorredores() {
        int numRegistros = (int) Math.ceil((double) f.length() / TAM_REGISTRO);
        try {
            System.out.println("\n----------------------DATOS CORREDORES---------------------\n");
            for (int i = 1; i <= numRegistros; i++) {
                Corredor corredor = buscarCorredor(i);
                if (corredor != null && !corredor.isBorrado()) {
                    System.out.println("+-------------------------------------------------+");
                    System.out.println(corredor.toString());
                }
            }
            System.out.println("+-------------------------------------------------+\n");
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void mostrarCorredor(int dorsal) {
        try {
            Corredor corredor = buscarCorredor(dorsal);
            String mensaje = corredor == null ? "corredor no existe." : corredor.toString();
            System.out.println(mensaje);
        } 
        catch (IOException e) {}
    }

    public Corredor buscarCorredor(int dorsal) throws IOException {
        Corredor corredor = new Corredor();
        raf.seek((dorsal - 1) * TAM_REGISTRO);

        if (raf.getFilePointer() < raf.length()) {
            corredor.setDorsal(raf.readInt());
            corredor.setTiempo(raf.readDouble());
            corredor.setBorrado(raf.readBoolean());
            corredor.setNombre(raf.readUTF());
            return corredor;
        } 
        else {
            return null;
        }
    }

    public boolean existeCorredor(String nombreCorredor) {
        int numRegistros = (int) Math.ceil((double) f.length() / TAM_REGISTRO);
        boolean esExistente = false;
        try {
            for (int i = 1; i <= numRegistros; i++) {
                Corredor corredor = buscarCorredor(i);
                if (corredor != null && corredor.getNombre().equals(nombreCorredor)) {
                    esExistente = true;
                }
            }
            return esExistente;
        } 
        catch (IOException e) {
            return esExistente;
        }
    }

    public void modificarCorredor(int dorsal, String nuevoNombre, double nuevoTiempo) throws IOException {
        // Buscar el corredor existente en el archivo
        Corredor corredor = buscarCorredor(dorsal);
        if (corredor == null) {
            System.out.println("Corredor no encontrado con el dorsal: " + dorsal);
            return;
        }
        // Actualizar los datos del corredor
        corredor.setNombre(nuevoNombre);
        corredor.setTiempo(nuevoTiempo);
    
        // Sobrescribir el registro en el archivo
        raf.seek((dorsal - 1) * TAM_REGISTRO);  // Colocarse en la posición correcta
        escribirCorredor(corredor);  // escribe el corredor actualizado en el archivo
        System.out.println("El corredor con dorsal " + dorsal + " ha sido modificado.");
    }
    
    private void escribirCorredor(Corredor corredor) throws IOException {
        raf.writeInt(corredor.getDorsal());     // Escribir el dorsal
        raf.writeDouble(corredor.getTiempo());  // Escribir el tiempo
        raf.writeBoolean(corredor.isBorrado()); // Escribir si está dado de baja o no
        raf.writeUTF(corredor.getNombre());     // Escribir el nombre (Dejamos el Srt para el final para evitar problemas con los saltos de línea)
    }

    public void addCorredor(Corredor cor) throws IOException {
        raf.seek((cor.getDorsal() - 1) * TAM_REGISTRO);
        raf.writeInt(cor.getDorsal());
        raf.writeDouble(cor.getTiempo());
        raf.writeBoolean(cor.isBorrado());
        raf.writeUTF(cor.getNombre());
    }

    public int numReg() {
        try {
            return (int) Math.ceil(raf.length() / (TAM_REGISTRO * 1.0));
        } catch (IOException e) {
            return 0;
        }
    }

    public void darBajaCorredor(int dorsal) {
        try {
            Corredor corredor = buscarCorredor(dorsal);
            corredor.setBorrado(true);
            addCorredor(corredor);
            System.out.println("Se ha dado de baja al corredor: " + corredor.getNombre());
        } catch (IOException e) {
            System.out.println("Error al buscar el corredor.");
        }
    }
}
