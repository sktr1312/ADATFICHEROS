import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CrudCorredores gestor = new CrudCorredores();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de opciones:");
            System.out.println("1.- Crear archivo");
            System.out.println("2.- Añadir registros");
            System.out.println("3.- Consultar un registro");
            System.out.println("4.- Consultar todos los registros");
            System.out.println("5.- Modificar un registro");
            System.out.println("6.- Borrar un registro");
            System.out.println("7.- Salir");
            System.out.print("Elige una opción <1-7>: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    try {
                        gestor.crearArchivo();
                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Introduce el nombre del corredor: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el dorsal del corredor: ");
                    int dorsal = scanner.nextInt();
                    System.out.print("Introduce el tiempo del corredor: ");
                    double tiempo = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer
                    try {
                        gestor.añadirRegistro(new Corredor(nombre, dorsal, tiempo));
                    } catch (IOException e) {
                        System.out.println("Error al añadir el registro: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Introduce el dorsal del corredor a consultar: ");
                    int dorsalBuscar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    try {
                        gestor.consultarRegistro(dorsalBuscar);
                    } catch (IOException e) {
                        System.out.println("Error al consultar el registro: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        gestor.consultarTodos();
                    } catch (IOException e) {
                        System.out.println("Error al consultar los registros: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Introduce el dorsal del corredor a modificar: ");
                    int dorsalModificar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    try {
                        gestor.modificarRegistro(dorsalModificar);
                    } catch (IOException e) {
                        System.out.println("Error al modificar el registro: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Introduce el dorsal del corredor a borrar: ");
                    int dorsalBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    try {
                        gestor.borrarRegistro(dorsalBorrar);
                    } catch (IOException e) {
                        System.out.println("Error al borrar el registro: " + e.getMessage());
                    }
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
