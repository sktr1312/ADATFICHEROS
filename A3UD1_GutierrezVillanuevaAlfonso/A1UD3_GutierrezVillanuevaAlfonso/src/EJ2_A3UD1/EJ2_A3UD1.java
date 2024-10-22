package EJ2_A3UD1;

// Escribir un programa para crear un archivo binario con valores numéricos enteros que sean consecutivos, 

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import utiles.ExceptionSecuenciaInvalida;
import utiles.Utiles;

// es decir, que sea mayor o igual que el anterior. Los datos se irán solicitando al usuario. El proceso terminará 
// cuando se introduzca cualquier carácter. A continuación visualiza en pantalla el contenido de este archivo. 
// Debes controlar todos los posibles errores que se puedan generar. Si el fichero existe, se visualizará un 
// mensaje, permitiendo elegir si se quiere remplazar el contenido o se quiere cancelar la operación de escritura.
// Crea tu propia excepción para controlar que el número introducido conserve la secuencia.
public class EJ2_A3UD1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            System.out.print("Escribe el nombre del archivo: ");
            String path = sc.nextLine();
            if (Utiles.comprobarfichero(path)) {
                Utiles.pintarArchivo(path);
                System.out.println("El archivo ya existe eliga que hacer:");
                System.out.println("1: Reemplazar contenido");
                System.out.println("2: Cancelar escritura");
                opcion = sc.nextInt();
                if (opcion == 1) {
                    try {
                        new File(path).delete();
                        Utiles.crearFicheroInts(path);
                    } catch (ExceptionSecuenciaInvalida e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Se cancelo la escritura");
                }
            } else {
                try {
                    Utiles.crearFicheroInts(path);
                } catch (ExceptionSecuenciaInvalida e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
