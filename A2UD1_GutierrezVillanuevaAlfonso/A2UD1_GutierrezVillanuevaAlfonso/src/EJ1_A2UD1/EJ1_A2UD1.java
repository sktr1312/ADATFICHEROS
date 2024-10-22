package EJ1_A2UD1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Realizar un programa que permita visualizar la siguiente información de las unidades instaladas en el equipo por
// pantalla o volcarla a un fichero. Dependiendo de la respuesta, se realizará la acción deseada. Ejemplo de salida_
public class EJ1_A2UD1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu de opciones");
        System.out.println("-----------------");
        System.out.println("[P] listado por pantalla");
        System.out.println("[D] listado a un fichero");
        System.out.println("-----------------");
        System.out.print("Elija opción------------>: ");
        String opcion = scanner.nextLine().toUpperCase();

        File[] unidades = File.listRoots();

        StringBuilder resultado = new StringBuilder();
        resultado.append("--- LISTANDO UNIDADES ---\n");

        for (File unidad : unidades) {
            long espacioLibre = unidad.getFreeSpace();
            long espacioOcupado = unidad.getTotalSpace() - espacioLibre;
            long espacioTotal = unidad.getTotalSpace();

            resultado.append("Unidad " + unidad.getAbsolutePath() + "\n");
            resultado.append("Espacio libre: " + espacioLibre + " bytes (" + bytesToGigas(espacioLibre) + " GB)\n");
            resultado.append(
                    "Espacio ocupado: " + espacioOcupado + " bytes (" + bytesToGigas(espacioOcupado) + " GB)\n");
            resultado.append("Espacio Total: " + espacioTotal + " bytes (" + bytesToGigas(espacioTotal) + " GB)\n");
            resultado.append("\n");
        }

        if (opcion.equals("P")) {
            System.out.println(resultado.toString());
        } else if (opcion.equals("D")) {
            try (FileWriter writer = new FileWriter("listado_unidades.txt");){                
                writer.write(resultado.toString());
                System.out.println("El listado ha sido guardado en 'listado_unidades.txt'");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir el fichero.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    private static String bytesToGigas(long bytes) {
        return String.format("%.3f", (double) bytes / (1024 * 1024 * 1024));
    }
}
