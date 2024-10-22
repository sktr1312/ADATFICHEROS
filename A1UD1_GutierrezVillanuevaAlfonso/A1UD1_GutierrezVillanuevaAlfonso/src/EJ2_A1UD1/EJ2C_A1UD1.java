package EJ2_A1UD1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
// C.Cambiar lo necesario ahora para que los resultados se escriban en un fichero de texto llamado Datos.txt,
// como se muestra a continuación:
public class EJ2C_A1UD1 {

    public static void main(String[] args) throws IOException {
        try (PrintStream fileOut = new PrintStream(new FileOutputStream("Datos.txt"))) {
            System.out.print("Leer cadena: ");
            String s = Teclado.leer();
            System.out.print("Leer caracter: ");
            char car = Teclado.leerChar();
            System.out.print("Leer numero entero: ");
            int num1 = Teclado.leerInt();
            System.out.print("Leer numero double: ");
            double num2 = Teclado.leerDouble();
            // Escribir los datos en el archivo
            fileOut.println("cadena: " + s);
            fileOut.println("caracter: " + car);
            fileOut.println("entero: " + num1);
            fileOut.println("numero real double: " + num2);
        }
    }
}
