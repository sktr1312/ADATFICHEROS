package EJ4_A3UD1;
// Crear un programa que visualice en pantalla el número de veces que se repite cada número en el fichero 

// binario

import java.util.Scanner;
import utiles.Utiles;

public class EJ4_A3UD1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el nombre del archivo: ");
        String path = sc.nextLine();
        Utiles.frecuenciaIntsArchivo(path);
        sc.close();
    }
}
