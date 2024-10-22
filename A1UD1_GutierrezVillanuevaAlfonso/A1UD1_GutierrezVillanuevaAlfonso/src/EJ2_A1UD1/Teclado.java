package EJ2_A1UD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static String leer() {
        String cadena = null;
        try {
            cadena = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cadena;
    }

    static char leerChar() {
        char caracter = 0;
        try {
            caracter = reader.readLine().charAt(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return caracter;
    }

    static int leerInt() {
        int entero = 0;
        try {
            entero = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("No es un numero entero");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return entero;
    }

    static double leerDouble() {
        double decimal = 0;
        try {
            decimal = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("No es un numero decimal");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return decimal;
    }

    public static int leerPositivo() throws IOException {
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
            }
        }
        return positivo;
    }
}


