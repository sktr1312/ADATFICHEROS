package EJ3_A2UD1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class EJ3_A2UD2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir al usuario el directorio
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();

        // Crear un objeto File a partir de la ruta introducida
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            System.out.println("La ruta especificada no existe");
        } else if (!directorio.isDirectory()) {
            System.out.println("La ruta no es un directorio");
        } else {
            System.out.println("--- LISTANDO EL DIRECTORIO " + ruta + " ---");
            listarDirectorios(directorio, 0);
        }
        sc.close();
    }

    public static void listarDirectorios(File directorio, int nivel) {
        File[] listaArchivos = directorio.listFiles();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        NumberFormat nf = NumberFormat.getInstance();

        if (listaArchivos != null) {
            for (File archivo : listaArchivos) {
                String tipo = archivo.isDirectory() ? "<DIR>" : "<FICHERO>";
                String fechaModificacion = sdf.format(archivo.lastModified());
                long espacio = archivo.getTotalSpace() / 1024;
                StringBuilder salida = new StringBuilder("-");
                for (int i = 0; i < nivel; i++) {
                    salida.append("---");
                }
                salida.append("| ").append(archivo.getName()).append(" ").append(tipo).append(" ").append(!archivo.isDirectory() ? nf.format(espacio) + " Kbytes " + fechaModificacion : "");
                System.out.println(salida);

                if (archivo.isDirectory()) {
                    listarDirectorios(archivo, nivel + 1);
                }
            }
        }
    }
}