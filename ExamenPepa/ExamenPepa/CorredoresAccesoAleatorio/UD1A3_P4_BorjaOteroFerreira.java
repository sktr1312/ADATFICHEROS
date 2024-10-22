/*@Author : Borja Otero Ferreiera  */
package ud1a3_p4;
import java.util.Scanner;

public class UD1A3_P4_BorjaOteroFerreira {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            mostrarMenu(); 
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1:
                    addRegistro(); break;
                case 2:
                    consultarRegistro(); break;
                case 3:
                    mostrarCorredores(); break;
                case 4:
                    modificarRegistro(); break;
                case 5:
                    borrar(); break;
                case 6: 
                    sc.close(); 
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    public static void addRegistro() {
        String nombre;
        double tiempo;
        while (true) {
            System.out.println("Introduce el nombre del corredor (o '*' para terminar):");
            nombre = sc.nextLine();
            if (nombre.equals("*")) {
                break;
            }
            System.out.println("Introduce el tiempo del corredor:");
            while (true) {
                String tiempoStr = sc.nextLine().replace(',', '.'); // Cambiar coma a punto
                try {
                    tiempo = Double.parseDouble(tiempoStr);
                    break; // Salir del bucle si la conversión es exitosa
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, introduce un número válido para el tiempo.");
                }
            }
            // Agregar el corredor
            CrudCorredor.addCorredor(nombre, tiempo);
        }
    }

    public static void consultarRegistro(){
        System.out.println("Introduce el dorsal del corredor");
        int dorsal = sc.nextInt();
        CrudCorredor.mostrarCorredor(dorsal);
    }

    public static void modificarRegistro() {
        String nombre; 
        int dorsal;
        double tiempo;
        do {
            System.out.println("Introduce el dorsal del corredor:");
            dorsal = sc.nextInt(); 
            sc.nextLine();  // Limpiar el buffer del scanner
            System.out.println("Introduce el nuevo nombre del corredor:");
            nombre = sc.nextLine();
            System.out.println("Introduce el nuevo tiempo del corredor:");
            tiempo = sc.nextDouble();
        } while (!CrudCorredor.modificarCorredor(dorsal, nombre, tiempo));  // Repetir si no se encuentra el corredor
    }

    public static void borrar(){
        System.out.println("Introduce el dorsal del corredor");
        int dorsal = sc.nextInt();
        CrudCorredor.darBajaCorredor(dorsal);
    }

    public static void mostrarCorredores(){
        CrudCorredor.mostrarCorredores();
    }
    
    private static void mostrarMenu() {
        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("1.- Añadir registros");
        System.out.println("2.- Consultar un registro");
        System.out.println("3.- Consultar todos los registros");
        System.out.println("4.- Modificar un registro");
        System.out.println("5.- Borrar");
        System.out.println("6.- Salir");
        System.out.print("Elige una opción <1-6>: ");
    }
}
