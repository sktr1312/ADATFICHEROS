package EJ1_A3P4UD1;

import java.io.IOException;

public class CrudArchivos {

    private static LeerEscribirArchivo lea = new LeerEscribirArchivo("Corredores.dat");

    public static void crearArchivo() {
        if (lea.exists()) {
            lea.abrirArchivo();
            System.out.println("El archivo ya existe con un total de " + lea.numRegistros() + " registros");
            lea.cerrarArchivo();
        } else {
            lea.crearArchivo();
            System.out.println("Archivo creado");
        }
    }

    public static void anhadirRegistros(String nombre, double tiempo) {
        lea.abrirArchivo();
        lea.escribirRegistro(lea.numRegistros() + 1, nombre, tiempo, false);
        lea.cerrarArchivo();

    }

    public static Corredor recuperaCorredor(int dorsal) throws IOException {
        Corredor corredor;
        lea.abrirArchivo();
        corredor = lea.recuperarRegistro(dorsal);
        lea.cerrarArchivo();
        return corredor;

    }

    public static void consultarRegistro(int dorsal) throws IOException {
        Corredor corredor;
        lea.abrirArchivo();
        corredor = lea.recuperarRegistro(dorsal);
        lea.cerrarArchivo();
        if (corredor != null) {
            System.out.println(corredor.toString());
        } else {
            System.out.println("No se ha encontrado el corredor con dorsal " + dorsal);
        }
    }

    public static void consultarRegistros() throws IOException {
        Corredor corredor;
        lea.abrirArchivo();
        for (int i = 1; i <= lea.numRegistros(); i++) {
            corredor = lea.recuperarRegistro(i);
            if (!corredor.isBorrado()) {
                System.out.println(corredor.toString());
            }
        }
        lea.cerrarArchivo();
    }

    public static void borrarRegistro(int dorsal) {
        Corredor corredor;
        lea.abrirArchivo();
        corredor = lea.recuperarRegistro(dorsal);
        if (corredor != null) {
            corredor.setBorrado(true);
            lea.escribirRegistro(dorsal, corredor.getNombre(), corredor.getTiempo(), true);
        } else {
            System.out.println("No se ha encontrado el corredor con dorsal " + dorsal);
        }
        lea.cerrarArchivo();
    }

    public static void modificarCorredor(int dorsal, String nombre, double tiempo) {
        Corredor corredor;
        lea.abrirArchivo();
        corredor = lea.recuperarRegistro(dorsal);
        if (corredor != null) {
            corredor.setNombre(nombre);
            corredor.setTiempo(tiempo);
            lea.escribirRegistro(dorsal, corredor.getNombre(), corredor.getTiempo(), false);
        } else {
            System.out.println("No se ha encontrado el corredor con dorsal " + dorsal);
        }
        lea.cerrarArchivo();
    }

    public static int numRegistros() {
        lea.abrirArchivo();
        int numRex = lea.numRegistros();
        lea.cerrarArchivo();
        return numRex;
    }

    public static LeerEscribirArchivo getLea() {
        return lea;
    }

    public static void setLea(LeerEscribirArchivo lea) {
        CrudArchivos.lea = lea;
    }
}
