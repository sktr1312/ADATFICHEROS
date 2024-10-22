package EJ2_A3P2UD1;

// A partir de un fichero de texto con el formato CURSO/NUMERO/ALUMNO crear un directorio por 
// cada curso y dentro de este un directorio por cada alumno perteneciente a ese curso. En un fichero de texto 
// llamado ficherolog.txt se irá escribiendo el éxito o fracaso en la creación de cada directorio de alumnos
// Ejemplo de fichero de log
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EJ2_A3P2UD1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Escriba el archivo de entrada: ");
            String inputFilePath = sc.nextLine();
            String logFilePath = "ficherolog.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath)); 
            BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("/");
                    if (parts.length == 3) {
                        String curso = parts[0];
                        String alumno = parts[1]+"-"+parts[2].toUpperCase();

                        File cursoDir = new File(curso);
                        if (!cursoDir.exists()) {
                            if (cursoDir.mkdirs()) {
                                logWriter.write("Directorio creado: " + curso + "\n");
                            } else {
                                logWriter.write("Error al crear el directorio: " + curso + "\n");
                            }
                        }

                        File alumnoDir = new File(cursoDir, alumno);
                        if (!alumnoDir.exists()) {
                            if (alumnoDir.mkdirs()) {
                                logWriter.write("Directorio creado: " + curso + "/" + alumno + "\n");
                            } else {
                                logWriter.write("Error al crear el directorio: " + curso + "/" + alumno + "\n");
                            }
                        }
                    } else {
                        logWriter.write("Formato incorrecto en la línea: " + line + "\n");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        }
    }
}
