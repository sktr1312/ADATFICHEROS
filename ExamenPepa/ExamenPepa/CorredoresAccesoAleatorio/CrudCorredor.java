/*@Author : Borja Otero Ferreiera  */
package ud1a3_p4;
import java.io.IOException;

public class CrudCorredor {

    static LeerEscribirAleatorio lea = new LeerEscribirAleatorio("corredores.dat");
    
    public static void addCorredor(String nombre, double tiempo) {

        lea.abrirArchivo();
        boolean existeCorredor = lea.existeCorredor(nombre);
        lea.cerrarArchivo();
        if (existeCorredor) {
            System.out.println("No se ha añadido ya que existe el corredor");
            return;
        }
        lea.abrirArchivo();
        int dorsal = lea.numReg() + 1;
        lea.cerrarArchivo();
        Corredor c = new Corredor(nombre, dorsal, tiempo);
        lea.abrirArchivo();
        try {
            lea.addCorredor(c);
        } catch (IOException e) {
            System.out.println("Error al añadir el corredor");
        }
        lea.cerrarArchivo();

    }

    public static void mostrarCorredores() {
        lea.abrirArchivo();
        lea.mostrarCorredores();
        lea.cerrarArchivo();
    }

    public static void mostrarCorredor(int dorsal) {
        lea.abrirArchivo();
        lea.mostrarCorredor(dorsal);
        lea.cerrarArchivo();
    }

    public static void darBajaCorredor(int dorsal) {
        lea.abrirArchivo();
        lea.darBajaCorredor(dorsal);
        lea.cerrarArchivo();
    }

    public static boolean modificarCorredor(int dorsal, String nombre, double tiempo) {
        Corredor corredor = null;
        boolean existe = false; 
        lea.abrirArchivo();
        try {
            corredor = lea.buscarCorredor(dorsal);
            if (corredor != null) {
                existe = true;
                lea.modificarCorredor(dorsal, nombre, tiempo);// Modificar el corredor
                System.out.println("Corredor con dorsal " + dorsal + " modificado exitosamente.");
            } else {
                System.out.println("El corredor con dorsal " + dorsal + " no existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar o modificar el corredor.");
        } finally {
            lea.cerrarArchivo();
        }
        return existe;  // Retornar true si el corredor fue encontrado y modificado, false si no.
    }
    

}
