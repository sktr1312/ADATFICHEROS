package com.mycompany.EJ1_A3P3UD1;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class EJ1_A3P3UD1 {

    static String path;

    public static void main(String[] args) throws IOException {
        boolean creado = false;
        int opcion;
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
                    case 1 -> {
                        crearArchivo(sc);
                        creado = !creado;

                    }
                    case 2 -> {
                        if (creado) {
                            anhadirCorredor(sc);
                        } else {
                            System.out.println("Primero debe crear un archivo");
                        }
                    }
                    case 3 -> {
                        if (creado) {
                            consultarRegistro(sc);
                        } else {
                            System.out.println("Primero debe crear un archivo");
                        }
                    }
                    case 4 -> {
                        if (creado) {
                            CrudArchivos.consultarRegistros();
                        } else {
                            System.out.println("Primero debe crear un archivo");
                        }}

                    
                
                case 5 -> {
                        if (creado) {
                            modificarRegistro(sc);
                        } else {
                            System.out.println("Primero debe crear un archivo");
                        }
                    }
                    case 6 -> {
                        if (creado) {
                            borrarRegistro(sc);
                        } else {
                            System.out.println("Primero debe crear un archivo");
                        }

                    }
                    case 7 -> {
                        System.out.println("Saliendo del programa");
                    }
                    default -> {
                        System.out.println("Opción no válida");
                        opcion = 0;
                    }
                }
            } while (opcion != 7);
            }
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

        TreeSet<Corredor> corredores = new TreeSet<>();
        // lectura anticipada de datos del corredor
        System.out.println("Introduzca el nombre del corredor (o * para salir): ");
        String nombre = sc.nextLine();
        while (!nombre.equals("*")) {
            System.out.println("Introduzca el dorsal del corredor: ");
            String dorsalInput = sc.nextLine();
            // convertir la entrada a un número
            int dorsal;
            try {
                dorsal = Integer.parseInt(dorsalInput);
            } catch (NumberFormatException e) {
                System.out.println("Dorsal no válido. Intente nuevamente.");
                continue; // Volver al inicio del bucle
            }
            // comprobar si el dorsal ya existe
            if (CrudArchivos.comprobarDorsal(dorsal) || corredores.contains(new Corredor("", dorsal, 0))) {
                System.out.println("El dorsal ya existe. Intente con otro dorsal.");
                continue;
            }
            System.out.println("Introduzca el tiempo del corredor: ");
            String tiempoInput = sc.nextLine();
            double tiempo;
            try {
                tiempo = Double.parseDouble(tiempoInput);
            } catch (NumberFormatException e) {
                System.out.println("Tiempo no válido. Intente nuevamente.");
                continue;
            }
            if (corredores.add(new Corredor(nombre, dorsal, tiempo))) {
                System.out.println("Corredor añadido");
                // Segunda lectura de datos del corredor
                System.out.println("Introduzca el nombre del corredor (o * para salir): ");
                nombre = sc.nextLine();
            } else {
                System.out.println("Error al añadir el corredor: " + new Corredor(nombre, dorsal, tiempo).toString());
            }
        }
        CrudArchivos.anhadirRegistros(corredores);
    }

    private static void crearArchivo(Scanner sc) {

        System.out.println("Nombre del archivo");
        path = sc.nextLine();
        if (new File(path).exists() && new File(path).isFile()) {
            System.out.println("El archivo ya existe desea sobreescribirlo? (s/n)");
            if (sc.nextLine().equals("s")) {
                new File(path).delete();
            }
        }

        CrudArchivos.crearArchivo(path);
        anhadirCorredor(sc);
    }

    private static void mostrarMenu() {

        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("\s1.-Crear archivo");
        System.out.println("\s2.-Añadir registros");
        System.out.println("\s3.-Consultar un registro");
        System.out.println("\s4.-Consultar todos los registros");
        System.out.println("\s5.-Modificar un registro");
        System.out.println("\s6.-Borrar registro");
        System.out.println("\s7.-Salir");
        System.out.print("Seleccione una opción: ");

    }
}
