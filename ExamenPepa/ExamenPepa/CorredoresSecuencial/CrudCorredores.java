import java.io.*;
import java.util.Scanner;

public class CrudCorredores {
    private final String nombreArchivo = "corredores.dat";

    public void crearArchivo() throws IOException {
        EscribirFichero escritor = new EscribirFichero(nombreArchivo);

        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("El archivo ya existe. ¿Desea sobrescribirlo? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                escritor.abrirArchivo();
            }
        }
        System.out.println("Archivo creado exitosamente.");
    }

    public void añadirRegistro(Corredor corredor) throws IOException {
        EscribirFichero escritor = new EscribirFichero(nombreArchivo);
        escritor.abrirArchivo();
        escritor.add(corredor);
        escritor.cerrarArchivo();
    }

    public void consultarTodos() throws IOException {
        LeerFichero lector = new LeerFichero(nombreArchivo);
        lector.abrirArchivo();
        lector.mostrarTodos();
        lector.cerrarArchivo();
    }

    public void modificarRegistro(int dorsalModificar) throws IOException {
        File archivoAux = new File("auxiliar.dat");
        File archivoOriginal = new File(nombreArchivo);
    
        if (!archivoOriginal.exists()) {
            System.out.println("El archivo no ha sido creado.");
            return;
        }
    
        LeerFichero lector = new LeerFichero(nombreArchivo);
        EscribirFichero escritorAux = new EscribirFichero("auxiliar.dat");
    
        lector.abrirArchivo();
        escritorAux.abrirArchivo();
    
        Corredor corredor;
        boolean encontrado = false;
    
        while ((corredor = lector.leerObjeto()) != null) {
            int dorsal = corredor.getDorsal();
            if (dorsal == dorsalModificar) {
                encontrado = true;
                System.out.println("Registro encontrado: " + corredor.toString());
                Scanner scanner = new Scanner(System.in);
                System.out.print("Nuevo nombre del corredor: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nuevo tiempo del corredor: ");
                double nuevoTiempo = scanner.nextDouble();
                scanner.nextLine();
    
                Corredor corredorModificado = new Corredor(nuevoNombre, dorsal, nuevoTiempo);
                escritorAux.add(corredorModificado);
            } else {
                escritorAux.add(corredor);
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
    
        LeerFichero lector = new LeerFichero(nombreArchivo);
        EscribirFichero escritorAux = new EscribirFichero("auxiliar.dat");
    
        lector.abrirArchivo();
        escritorAux.abrirArchivo();
    
        Corredor corredor;
        boolean encontrado = false;
    
        while ((corredor = lector.leerObjeto()) != null) {
            int dorsal = corredor.getDorsal();
            if (dorsal == dorsalBorrar) {
                encontrado = true;
                System.out.println("Registro eliminado: " + corredor);
            } else {
                escritorAux.add(corredor);
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
            LeerFichero lector = new LeerFichero(nombreArchivo);
            lector.abrirArchivo();
            lector.mostrarPorDorsal(dorsalBuscar);
            lector.cerrarArchivo();
         
        }
    }

