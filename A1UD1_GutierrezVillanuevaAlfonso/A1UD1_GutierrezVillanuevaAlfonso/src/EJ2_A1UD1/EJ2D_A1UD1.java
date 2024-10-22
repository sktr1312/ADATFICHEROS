package EJ2_A1UD1;


import java.io.IOException;
import java.io.PrintStream;
// D.Añadir un método llamado leerPositivo() en la clase Teclado.java para que solo nos permita leer
// números enteros positivos. Si el dato no es correcto, hay que volver a introducirlo. Crea tu propia
// excepción para controlar si el número introducido es positivo.
// Pon ejemplos de llamada al método
public class EJ2D_A1UD1 {
    public static void main(String[] args) throws IOException {
        PrintStream out = System.out;
        out.print("Leer cadena: ");
        String s = Teclado.leer();
        out.print("Leer caracter: ");
        char car = Teclado.leerChar();
        out.print("Leer numero entero: ");
        int num1 = Teclado.leerInt();
        out.print("Leer numero double: ");
        double num2 = Teclado.leerDouble();
        out.print("Leer numero positivo: ");
        double num3 = Teclado.leerPositivo();
        out.println(" cadena: " + s);
        out.println(" caracter: " + car);
        out.println(" entero: " + num1);
        out.println(" numero real double: " + num2);
        out.println(" numero positivo: " + num3);
    }
}