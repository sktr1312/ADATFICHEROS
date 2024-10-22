package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class Utiles {
    // Método para leer un archivo de texto y devolver su contenido como una cadena
    public static String leerArchivoTextoComoCadena(String rutaArchivo) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(rutaArchivo));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    // Método para escribir una cadena en un archivo de texto
    public static void escribirCadenaEnArchivoTexto(String rutaArchivo, String contenido) throws IOException {
        Files.write(Paths.get(rutaArchivo), contenido.getBytes(StandardCharsets.UTF_8));
    }

    // Método para copiar un archivo de origen a un archivo de destino
    public static void copiarArchivo(String origen, String destino) throws IOException {
        Path origenPath = Paths.get(origen);
        Path destinoPath = Paths.get(destino);
        Files.copy(origenPath, destinoPath);
    }

    // Método para eliminar un archivo
    public static void eliminarArchivo(String rutaArchivo) throws IOException {
        Files.deleteIfExists(Paths.get(rutaArchivo));
    }

    // Método para mostrar un diálogo de selección de carpeta en JavaFX y devolver
    // la ruta seleccionada
    public static String seleccionarCarpeta(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar carpeta");
        File selectedDirectory = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        if (selectedDirectory != null) {
            return selectedDirectory.getAbsolutePath();
        } else {
            return null;
        }
    }

    // Método para leer un archivo binario y devolver su contenido como un
    // InputStream
    public static InputStream leerArchivoBinarioComoStream(String rutaArchivo) throws IOException {
        return new FileInputStream(rutaArchivo);
    }

    // Método para escribir un InputStream en un archivo binario
    public static void escribirStreamEnArchivoBinario(String rutaArchivo, InputStream inputStream) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(rutaArchivo)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    // Método para leer un archivo de texto y devolver su contenido como una lista de cadenas
    public static List<String> leerArchivoTexto(String rutaArchivo) throws IOException {
        return Files.readAllLines(Paths.get(rutaArchivo));
    }

    // Método para escribir una lista de cadenas en un archivo de texto
    public static void escribirArchivoTexto(String rutaArchivo, List<String> lineas) throws IOException {
        Files.write(Paths.get(rutaArchivo), lineas);
    }

    // Método para leer un archivo binario y devolver su contenido como un arreglo de bytes
    public static byte[] leerArchivoBinario(String rutaArchivo) throws IOException {
        Path path = Paths.get(rutaArchivo);
        return Files.readAllBytes(path);
    }

    // Método para escribir un arreglo de bytes en un archivo binario
    public static void escribirArchivoBinario(String rutaArchivo, byte[] bytes) throws IOException {
        Path path = Paths.get(rutaArchivo);
        Files.write(path, bytes);
    }

    // Método para mostrar un diálogo de selección de archivo en JavaFX y devolver la ruta seleccionada
    public static String seleccionarArchivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    // Método para mostrar un diálogo de guardado de archivo en JavaFX y devolver la ruta seleccionada
    public static String guardarArchivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

}
