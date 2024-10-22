package EJ1_A1UD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Crear un programa que pida dos números enteros al usuario y saque el siguiente resultado por pantalla: la
// suma, resta, multiplicación, división entera, división real y resto de los dos números introducidos.
// El programa debe controlar y tratar los errores que puedan producirse. Si cualquiera de los números
// introducidos no es un número entero se deberá pedir de nuevo. Implementa un método llamado leerEntero(),
// que haga la función de pedir los números correctos.
// Se debe utilizar la clase BufferedReader para la lectura de datos por teclado. Ejemplos de
// funcionamiento:
public class EJ1_A1UD1 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num1 = leerEntero("Introduce el primer número: ", reader);
            int num2 = leerEntero("Introduce el segundo número: ", reader);

            int suma = num1 + num2;
            int resta = num1 - num2;
            int multiplicacion = num1 * num2;
            int divisionEntera = num1 / num2;
            double divisionReal = (double) num1 / num2;
            int resto = num1 % num2;

            System.out.println("La suma de los números es: " + suma);
            System.out.println("La resta de los números es: " + resta);
            System.out.println("La multiplicación de los números es: " + multiplicacion);
            System.out.println("La división entera de los números es: " + divisionEntera);
            System.out.println("La división real de los números es: " + divisionReal);
            System.out.println("El resto de los números es: " + resto);
        } catch (IOException e) {
            System.out.println("Ocurrió un error de entrada/salida.");
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero.");
        }
    }

    public static int leerEntero(String mensaje, BufferedReader reader) throws IOException {
        int numero;
        numero = 0;
        boolean esEntero = false;
        while (!esEntero) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(reader.readLine());
                esEntero = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
            }
        }

        return numero;
    }
}
