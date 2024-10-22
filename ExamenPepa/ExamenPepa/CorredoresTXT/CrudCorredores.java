import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CrudCorredores {
    private final String nombreArchivo = "corredores.dat";

    public void crearArchivo() throws IOException {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("El archivo ya existe. ¿Desea sobrescribirlo? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                return;
            }
        }
        archivo.createNewFile();
        System.out.println("Archivo creado exitosamente.");
    }

    public void añadirRegistro(Corredor corredor) throws IOException {
        EscribirTxt escritor = new EscribirTxt(nombreArchivo);
        escritor.abrirArchivo();
        escritor.escribirLinea(corredor.toString());
        escritor.cerrarArchivo();
    }

    public void consultarTodos() throws IOException {
        LeerTxt lector = new LeerTxt(nombreArchivo);
        lector.abrirArchivo();
        String linea;
        while ((linea = lector.leerLinea()) != null) {
            System.out.println(linea);
        }
        lector.cerrarArchivo();
    }

    public void modificarRegistro(int dorsalModificar) throws IOException {
        File archivoAux = new File("auxiliar.dat");
        File archivoOriginal = new File(nombreArchivo);
    
        if (!archivoOriginal.exists()) {
            System.out.println("El archivo no ha sido creado.");
            return;
        }
    
        LeerTxt lector = new LeerTxt(nombreArchivo);
        EscribirTxt escritorAux = new EscribirTxt("auxiliar.dat");
    
        lector.abrirArchivo();
        escritorAux.abrirArchivo();
    
        String linea;
        boolean encontrado = false;
    
        while ((linea = lector.leerLinea()) != null) {
            String[] datos = linea.split(", ");
            int dorsal = Integer.parseInt(datos[1].split(": ")[1]);
    
            if (dorsal == dorsalModificar) {
                encontrado = true;
                System.out.println("Registro encontrado: " + linea);
                Scanner scanner = new Scanner(System.in);
    
                System.out.print("Nuevo nombre del corredor: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nuevo tiempo del corredor: ");
                double nuevoTiempo = scanner.nextDouble();
    
                Corredor corredorModificado = new Corredor(nuevoNombre, dorsal, nuevoTiempo);
                escritorAux.escribirLinea(corredorModificado.toString());
            } else {
                escritorAux.escribirLinea(linea);
            }
        }
    
        lector.cerrarArchivo();
        escritorAux.cerrarArchivo();
    
        if (encontrado) {
            archivoOriginal.delete();
            archivoAux.renameTo(archivoOriginal);
            System.out.println("Registro modificado correctamente.");
        } else {
            archivoAux.delete();
            System.out.println("No se encontró un registro con el dorsal proporcionado.");
        }
    }
    
    public void borrarRegistro(int dorsalBorrar) throws IOException {
        File archivoAux = new File("auxiliar.dat");
        File archivoOriginal = new File(nombreArchivo);
    
        if (!archivoOriginal.exists()) {
            System.out.println("El archivo no ha sido creado.");
            return;
        }
    
        LeerTxt lector = new LeerTxt(nombreArchivo);
        EscribirTxt escritorAux = new EscribirTxt("auxiliar.dat");
    
        lector.abrirArchivo();
        escritorAux.abrirArchivo();
    
        String linea;
        boolean encontrado = false;
    
        while ((linea = lector.leerLinea()) != null) {
            String[] datos = linea.split(", ");
            int dorsal = Integer.parseInt(datos[1].split(": ")[1]);
    
            if (dorsal == dorsalBorrar) {
                encontrado = true;
                System.out.println("Registro eliminado: " + linea);
            } else {
                escritorAux.escribirLinea(linea);
            }
        }
    
        lector.cerrarArchivo();
        escritorAux.cerrarArchivo();
    
        if (encontrado) {
            archivoOriginal.delete();
            archivoAux.renameTo(archivoOriginal);
            System.out.println("Registro borrado correctamente.");
        } else {
            archivoAux.delete();
            System.out.println("No se encontró un registro con el dorsal proporcionado.");
        }
    }
    public void consultarRegistro(int dorsalBuscar) throws IOException {
    File archivoOriginal = new File(nombreArchivo);

    if (!archivoOriginal.exists()) {
        System.out.println("El archivo no ha sido creado.");
        return;
    }

    LeerTxt lector = new LeerTxt(nombreArchivo);
    lector.abrirArchivo();

    String linea;
    boolean encontrado = false;

    while ((linea = lector.leerLinea()) != null) {
        String[] datos = linea.split(", ");
        int dorsal = Integer.parseInt(datos[1].split(": ")[1]);

        if (dorsal == dorsalBuscar) {
            encontrado = true;
            System.out.println("Registro encontrado: " + linea);
            break;
        }
    }

    lector.cerrarArchivo();

    if (!encontrado) {
        System.out.println("No se encontró un registro con el dorsal proporcionado.");
    }
}

    
}
