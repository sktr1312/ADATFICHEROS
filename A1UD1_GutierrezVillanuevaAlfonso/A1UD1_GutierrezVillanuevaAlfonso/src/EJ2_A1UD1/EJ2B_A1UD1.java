package EJ2_A1UD1;

import java.io.IOException;
import java.io.PrintStream;
// B.Cambiar lo que sea necesario el anterior código para utilizar la clase PrintStream en todas las sentencias
// de salida por pantalla.
public class EJ2B_A1UD1 {

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
        out.println(" cadena: " + s);
        out.println(" caracter: " + car);
        out.println(" entero: " + num1);
        out.println(" numero real double: " + num2);
    }
}
