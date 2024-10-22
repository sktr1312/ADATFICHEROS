package utiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Utiles {
    public static double generaNumeros(double VALOR_MINIMO, double VALOR_MAXIMO) {
        return (double) (Math.random() * (VALOR_MAXIMO - VALOR_MINIMO + 1) + VALOR_MINIMO);
    }

    public static boolean comprobarfichero(String fichero) {
        return new File(fichero).exists();
    }

    public static void guardarIntFichero(int num, String ruta) {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(ruta, true)))) {
            out.writeInt(num);

        } catch (FileNotFoundException e) {
            System.out.println("el archivo no se encontro");
        } catch (IOException e) {
            System.out.println("Ocurrio algun fallo");
        }
    }

    public static void pintarArchivo(String fichero) {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)))) {
            while (true) {
                System.out.println(in.readInt());

            }
        } catch (EOFException e) {
            System.out.println("FINAL ARCHIVO");
        } catch (IOException e) {
            System.out.println("I/O ERROR");
        }
    }

    public static void crearFicheroInts(String path) throws ExceptionSecuenciaInvalida {
        try (Scanner sc = new Scanner(System.in)) {
            String num;
            int numAnterior = 0;
            System.out.print("Introduzca un numero para empezar: (Introduzca un caracter para salir)");
            num = sc.nextLine();
            if (esNumero(num))
                numAnterior = Integer.parseInt(num);
            while (Utiles.esNumero(num)) {
                if (Integer.parseInt(num) < numAnterior) {
                    throw new ExceptionSecuenciaInvalida("El numero introducido no sigue la secuencia");
                }
                guardarIntFichero(Integer.parseInt(num), path);
                numAnterior = Integer.parseInt(num);
                System.out.print("Introduzca otro numero: (Introduzca un caracter para salir)");
                num = sc.nextLine();

            }
        } catch (NumberFormatException e) {
            System.out.println("no se pudo parsear el numero");
        }
    }

    private static boolean esNumero(String num) {
        boolean esNumero = true;
        if (num == null || num.trim().isEmpty()) {
            esNumero = false;
        } else {
            try {
                Integer.valueOf(num.trim());
            } catch (NumberFormatException e) {
                esNumero = false;
            }
        }
        return esNumero;
    }

    public static void mezclaFicheros(String fichero1, String fichero2) throws IOException {
        try (DataInputStream in1 = new DataInputStream(new FileInputStream(fichero1));
                DataInputStream in2 = new DataInputStream(new FileInputStream(fichero2));
                DataOutputStream out = new DataOutputStream(new FileOutputStream("Mezcla.dat"))) {

            Integer num1 = null, num2 = null;

            if (in1.available() > 0)
                num1 = in1.readInt();
            if (in2.available() > 0)
                num2 = in2.readInt();

            while (num1 != null || num2 != null) {
                if (num1 != null && (num2 == null || num1 < num2)) {
                    out.writeInt(num1);
                    num1 = in1.available() > 0 ? in1.readInt() : null;
                } else {
                    out.writeInt(num2);
                    num2 = in2.available() > 0 ? in2.readInt() : null;
                }
            }
        }
    }

    public static void frecuenciaIntsArchivo(String path) {
        Map<Integer, Integer> frecuencia = new LinkedHashMap<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
            while (true) {
                int numero = dis.readInt();
                frecuencia.put(numero, frecuencia.getOrDefault(numero, 0) + 1);
            }
        } catch (IOException e) {
        }


        for (Map.Entry<Integer, Integer> entry : frecuencia.entrySet()) {
            System.out.println("Número: " + entry.getKey() + " - Frecuencia: " + entry.getValue());
        }
    }

}
