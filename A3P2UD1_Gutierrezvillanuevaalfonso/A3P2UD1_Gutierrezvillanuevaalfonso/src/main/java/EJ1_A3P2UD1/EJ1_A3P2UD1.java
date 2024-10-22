package EJ1_A3P2UD1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// comandos (Nota: pueden especificarse varios archivos, como por ejemplo: "exercicio5-1 file1.txt file3.txt
// file2.txt"). Los archivos deben ser archivos de texto con la extensión txt. Escribe en un fichero de texto 
// llamado Salida.txt: el nombre de cada fichero, junto con el número de líneas del fichero. Si ocurre un error al 
// intentar leer uno de los ficheros, en el fichero salida.txt se graba un mensaje de error para el archivo, y se 
// deben procesar todos los ficheros restantes.

public class EJ1_A3P2UD1 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Por favor, especifica al menos un archivo de texto.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Salida.txt"))) {
            for (String archivo : args) {
                contarLineas(archivo, writer);
            }
        } catch (IOException e) {
            System.err.println("Error al crear o escribir en el archivo de salida: " + e.getMessage());
        }
    }

    private static void contarLineas(String nombreArchivo, BufferedWriter writer) {
        File archivo = new File(nombreArchivo);

        if (!archivo.exists() || !archivo.isFile() || !nombreArchivo.endsWith(".txt")) {
            try {
                writer.write("Error: El archivo " + nombreArchivo + " no es válido.\n");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo de salida: " + e.getMessage());
            }

        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                int lineas = 0;
                while (reader.readLine() != null) {
                    lineas++;
                }
                writer.write("Archivo: " + nombreArchivo + " - Número de líneas: " + lineas + "\n");
            } catch (IOException e) {
                try {
                    writer.write("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage() + "\n");
                } catch (IOException ex) {
                    System.err.println("Error al escribir en el archivo de salida: " + ex.getMessage());
                }
            }
        }
    }
}
