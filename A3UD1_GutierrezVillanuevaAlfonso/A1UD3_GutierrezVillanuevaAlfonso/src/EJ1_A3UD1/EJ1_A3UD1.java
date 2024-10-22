package EJ1_A3UD1;

import java.io.DataOutputStream;
import java.io.File;
import java.util.Scanner;

import utiles.Utiles;

// Crear un programa que genere 150 números enteros aleatorios comprendidos entre 20 y 80 y los grabe en 
// un fichero binario cuyo nombre será introducido por el usuario. A continuación visualiza en pantalla el 
// contenido de este archivo. Debes controlar todos los posibles errores que se puedan generar. Si el fichero 
// existe, se visualizará un mensaje, permitiendo elegir si se quiere remplazar el contenido o se quiere cancelar 
// la operación de escritura
public class EJ1_A3UD1 {

    static DataOutputStream out;

    public static void main(String[] args) throws Exception {
        int numsGenerar = 150;
        int valueMax = 80;
        int valueMin = 20;
        int opcion;
        System.out.print("Introduzca el nombre del fichero: ");
        StringBuilder fichero = new StringBuilder(new Scanner(System.in).nextLine());
        if (Utiles.comprobarfichero(fichero.toString())) {
            Utiles.pintarArchivo(fichero.toString());
            System.out.println("El archivo ya existe eliga que hacer:");
            System.out.println("1: Reemplazar contenido");
            System.out.println("2: Cancelar escritura");
            opcion = new Scanner(System.in).nextInt();
            if (opcion == 1) {
                new File(fichero.toString()).delete();
                for (int i = 0; i < numsGenerar; i++) {
                    Utiles.guardarIntFichero((int) Utiles.generaNumeros(valueMin, valueMax), fichero.toString());
                }

            } else
                System.out.println("Se cancelo la escritura");
        } else {
            for (int i = 0; i < numsGenerar; i++) {
                Utiles.guardarIntFichero((int) Utiles.generaNumeros(valueMin, valueMax), fichero.toString());
            }
            Utiles.pintarArchivo(fichero.toString());
        }
    }

}
