package EJ6_A2UD1;

import java.io.File;
import java.io.FilenameFilter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EJ6_A2UD1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir al usuario el directorio
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();

        System.out.print("Introduce la cadena a buscar: ");
        String cadenaUsuario = sc.nextLine();

        // Crear un objeto File a partir de la ruta introducida
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            System.out.println("La ruta especificada no existe");
        } else if (!directorio.isDirectory()) {
            System.out.println("La ruta no es un directorio");
        } else {
            System.out.println("--- LISTANDO EL DIRECTORIO " + ruta + " ---");
            listarDirectorios(directorio, cadenaUsuario, 0);
        }
        sc.close();
    }

    public static void listarDirectorios(File directorio, String cadenaUsuario, int nivel) {
        FilenameFilter filtro = (dir, name) -> name.contains(cadenaUsuario)
                || dir.isDirectory();
        File[] listaArchivos = directorio.listFiles(filtro);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        NumberFormat nf = NumberFormat.getInstance();

        if (listaArchivos != null) {
            for (File archivo : listaArchivos) {
                String tipo = archivo.isDirectory() ? "<DIR>" : "<FICHERO>";
                String fechaModificacion = sdf.format(archivo.lastModified());
                long espacio = archivo.getTotalSpace() / 1024;
                StringBuilder salida = new StringBuilder("-");
                if ((archivo.isDirectory() && archivo.listFiles().length > 0) || !archivo.isDirectory()) {

                    for (int i = 0; i < nivel; i++) {
                        salida.append("---");
                    }
                    salida.append("| " + archivo.getName() + " " + tipo + " "
                            + (!archivo.isDirectory() ? nf.format(espacio) + " Kbytes " + fechaModificacion : ""));
                    System.out.println(salida);
                }
                if (archivo.isDirectory()) {
                    listarDirectorios(archivo, cadenaUsuario, nivel + 1);
                }
            }
        }
    }
}
