
package EJ3_A1UD1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import EJ2_A1UD1.ExcepcionEnteroPositivo;

// a) Haz un programa para pedir por teclado n números enteros positivos y grábalos en un fichero de texto
// llamado NumerosPositivos.txt, separados por ; .
// N será introducido por teclado y deberá ser un número entero positivo.
// A continuación visualiza la información de este fichero.
// Clases para utilizar: PrintStream e Scanner para la escritura y lectura en el fichero.
// Para controlar el final de fichero en la lectura se hará capturando la excepción que puede ser lanzada al
// hacer la operación de lectura.
// El método main es el que se detalla a continuación y deberéis implementar los métodos que se indican.
// El control de la introducción de números enteros positivos se hará capturando una excepción propia
// que se habrá creado.
public class EJ3_A1UD1 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream ficheroSalida;

    public static void main(String[] args)
            throws IOException, ExcepcionEnteroPositivo {
        abrirFichero();
        System.out.println("Introduce el número de enteros positivos para  grabar en fichero:");
        int num = leerPositivo();
        for (int i = 1; i <= num; i++) {
            System.out.print("número " + i + ":");
            grabarFichero(leerPositivo());
        }
        cerrarFichero();
        LeerFichero();
        reader.close();
    }

    private static void cerrarFichero() {
        ficheroSalida.close();
    }

    private static void LeerFichero() {
        try (Scanner ficheroEntrada = new Scanner(new FileInputStream("NumerosPositivos.txt"))) {
            String[] nums = ficheroEntrada.next().split(";");
            for (String num : nums) {
                System.out.println(num);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero");
        } catch (IllegalStateException e) {
            System.out.println("Error al leer el fichero");
        }
    }

    private static void grabarFichero(int leerPositivo) {
        ficheroSalida.print(leerPositivo + ";");
    }

    private static int leerPositivo() {
        int positivo = 0;
        boolean esPositivo = false;
        while (!esPositivo) {
            try {
                positivo = Integer.parseInt(reader.readLine());
                if (positivo < 0) {
                    throw new ExcepcionEnteroPositivo("El numero introducido es negativo");
                }
                esPositivo = true;
            } catch (ExcepcionEnteroPositivo e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
            } catch (IOException e) {
                System.out.println("fallo.");
            }
        }
        return positivo;
    }

    private static void abrirFichero() {
        try {
            ficheroSalida = new PrintStream(new FileOutputStream("NumerosPositivos.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero");
        }
    }
}
