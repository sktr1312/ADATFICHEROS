package EJ4_A1UD1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Scanner;
import EJ2_A1UD1.ExcepcionEnteroPositivo;

// Ordena de menor a mayor el fichero NumeroPositivos y visualízalos ordenados los números. Para ello
// deberás utilizar una dinámica de memoria, por ejemplo un ArrayList y ordenar esta lista y volcarla otra vez
// al fichero. Utilizando las clases Scanner y PrintStream
public class EJ4_A1UD1 {


    public static void main(String[] args)
            throws IOException, ExcepcionEnteroPositivo {
        ArrayList<Integer> numsPositivos = LeerFichero();
        for (int num : numsPositivos) {
            System.out.println(num);
        }
        grabarFichero(numsPositivos);
    }


    private static ArrayList<Integer> LeerFichero() {
        ArrayList<Integer> numsPositivo = new ArrayList<>();
        try (Scanner ficheroEntrada = new Scanner(new FileInputStream("NumerosPositivos.txt"))) {
            for (String num : ficheroEntrada.next().split(";")) {
                numsPositivo.add(Integer.parseInt(num));
            }
            numsPositivo.sort(Comparator.naturalOrder());
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero");
        } catch (IllegalStateException e) {
            System.out.println("Error al leer el fichero");
        }
        return numsPositivo;
    }

    private static void grabarFichero(ArrayList<Integer> numsPositivo) {
       try(PrintStream ficheroSalida = new PrintStream(new FileOutputStream("NumerosPositivos.txt"))) {
            for (int num : numsPositivo) {
                ficheroSalida.print(num + ";");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero");
        }
    }


}
