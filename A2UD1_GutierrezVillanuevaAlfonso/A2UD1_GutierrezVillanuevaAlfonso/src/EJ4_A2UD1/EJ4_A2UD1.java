package EJ4_A2UD1;

import java.io.File;
import java.io.FilenameFilter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EJ4_A2UD1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el directorio
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = scanner.nextLine();

        // Pedir al usuario la extensión
        System.out.print("Introduce la extensión ");
        String extension = scanner.nextLine();

        // Crear un objeto File a partir de la ruta introducida
        File directorio = new File(ruta);

        // Comprobar si la ruta existe
        if (!directorio.exists()) {
            System.out.println("La ruta especificada no existe");
        } else if (!directorio.isDirectory()) {
            // Comprobar si no es un directorio
            System.out.println("La ruta no es un directorio");
        } else {
            // Listar el contenido del directorio
            System.out.println("--- LISTANDO EL DIRECTORIO " + ruta + " ---");

            FilenameFilter filtro = (dir, name) -> name.endsWith(extension);
            File[] listaArchivos = directorio.listFiles(filtro);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            for (File archivo : listaArchivos) {
                String tipo = archivo.isDirectory() ? "<DIR>" : "<FICHERO>";
                String fechaModificacion = sdf.format(archivo.lastModified());
                NumberFormat nf = NumberFormat.getInstance();
                long espacio = archivo.getTotalSpace() / 1024;
                StringBuilder salida = new StringBuilder("-|" + archivo.getName() + "\s" + tipo + "\s"
                        + (!archivo.isDirectory() ? nf.format(espacio) + "Kbytes " + fechaModificacion : ""));
                System.out.println(salida);

            }
        }

        scanner.close();
    }
}
