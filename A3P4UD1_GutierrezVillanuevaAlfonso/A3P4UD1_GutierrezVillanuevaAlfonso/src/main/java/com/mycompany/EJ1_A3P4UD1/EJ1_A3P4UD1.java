package EJ1_A3P4UD1;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EJ1_A3P4UD1 {

    public static void main(String[] args) throws IOException {
        int opcion;
        crearArchivo();

        try (Scanner sc = new Scanner(System.in)) {
            do {
                mostrarMenu();
                try {
                    opcion = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida");
                    sc.nextLine(); // Limpiar buffer
                    opcion = 0;
                    continue;
                }
                sc.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> anhadirCorredor(sc);

                    case 2 -> {
                        if (numRegistros() > 0) {
                            consultarRegistro(sc);
                        } else {
                            System.out.println("Primero debe añadir algun registro");
                        }
                    }
                    case 3 -> {
                        if (numRegistros() > 0) {
                            CrudArchivos.consultarRegistros();
                        } else {
                            System.out.println("Primero debe añadir algun registro");
                        }
                    }

                    case 4 -> {
                        if (numRegistros() > 0) {
                            modificarRegistro(sc);
                        } else {
                            System.out.println("Primero debe añadir algun registro");
                        }
                    }
                    case 5 -> {
                        if (numRegistros() > 0) {
                            borrarRegistro(sc);
                        } else {
                            System.out.println("Primero debe añadir algun registro");
                        }

                    }
                    case 6 -> {
                        System.out.println("Saliendo del programa");
                    }
                    default -> {
                        System.out.println("Opción no válida");
                        opcion = 0;
                    }
                }
            } while (opcion != 6);
        }
    }

    private static int numRegistros() {
        return CrudArchivos.numRegistros();
    }

    private static void borrarRegistro(Scanner sc) {
        System.out.println("Introduzca el dorsal del corredor a borrar: ");
        int dorsal = sc.nextInt();
        CrudArchivos.borrarRegistro(dorsal);

    }

    private static void consultarRegistro(Scanner sc) throws IOException {
        int dorsal;
        System.out.println("Introduzca el dorsal del corredor a consultar: ");
        dorsal = sc.nextInt();
        CrudArchivos.consultarRegistro(dorsal);

    }

    private static void modificarRegistro(Scanner sc) {
        int dorsal;
        Corredor corredor = null;
        System.out.println("Introduzca el dorsal del corredor a modificar: ");
        dorsal = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        try {
            corredor = CrudArchivos.recuperaCorredor(dorsal);
        } catch (IOException e) {
            System.out.println("Error al recuperar el corredor " + e.getMessage());
        }
        if (corredor != null) {
            System.out.println(corredor.toString());

            System.out.println("Introduzca el nombre nuevo del corredor: ");
            String nombre = sc.nextLine();
            System.out.println("Introduzca el tiempo nuevo del corredor: ");
            double tiempo = sc.nextDouble();
            CrudArchivos.modificarCorredor(dorsal, nombre, tiempo);

        }

    }

    private static void anhadirCorredor(Scanner sc) {

        // lectura anticipada de datos del corredor
        System.out.println("Introduzca el nombre del corredor (o * para salir): ");
        String nombre = sc.nextLine();
        while (!nombre.equals("*")) {
            System.out.println("Introduzca el tiempo del corredor: ");
            String tiempoInput = sc.nextLine();
            double tiempo;
            try {
                tiempo = Double.parseDouble(tiempoInput);
            } catch (NumberFormatException e) {
                System.out.println("Tiempo no válido. Intente nuevamente.");
                continue;
            }
            CrudArchivos.anhadirRegistros(nombre, tiempo);
            System.out.println("Introduzca el nombre del corredor (o * para salir): ");
            nombre = sc.nextLine();
        }

    }

    private static void crearArchivo() {
        CrudArchivos.crearArchivo();
    }

    private static void mostrarMenu() {

        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("\s1.-Añadir registros");
        System.out.println("\s2.-Consultar un registro");
        System.out.println("\s3.-Consultar todos los registros");
        System.out.println("\s4.-Modificar un registro");
        System.out.println("\s5.-Borrar un archivo");
        System.out.println("\s6.-Salir");
        System.out.print("Seleccione una opción: ");

    }

}
