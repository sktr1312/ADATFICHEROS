package com.mycompany.EJ1_A3P3UD1;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public class CrudArchivos {

    private static LeerArchivo leerArchivo;
    private static EscribirArchivo escribirArchivo;

    public static void crearArchivo(String archivo) {
        // La operación de crear archivo permitirá abrir un fichero para poder escribir
        // en él comprobando antes su
        // existencia. Si el archivo existe, se preguntará si se quiere sobrescribir. Se
        // introducirán los datos hasta que en el
        // nombre del participante se introduzca un *. No se deberá introducir un número
        // de dorsal repetido
        escribirArchivo = new EscribirArchivo(archivo);
        escribirArchivo.abrirArchivo();
        escribirArchivo.cerrarArchivo();
    }

    public static void anhadirRegistros(Set<Corredor> corredores) {
        if (escribirArchivo != null && escribirArchivo.exists()) {
            escribirArchivo.abrirArchivo();
            for (Corredor corredor : corredores) {
                escribirArchivo.anhadirObjeto(corredor);
            }
            escribirArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha podido añadir ");
        }
    }

    public static Corredor recuperaCorredor(int dorsal) throws IOException {
        Corredor corredor = null;
        boolean sw = false;
        if (comprobarDorsal(dorsal)) {
            leerArchivo = new LeerArchivo(escribirArchivo.getPath());
            leerArchivo.abrirArchivo();
            while (!sw) {
                try {
                    corredor = (Corredor) leerArchivo.leerRegistro();
                    if (corredor.getDorsal() == dorsal) {
                        sw = true;
                    }
                } catch (IOException e) {
                    sw = true;
                }
            }
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
        return corredor;
    }

    public static boolean comprobarDorsal(int dorsal) {
        boolean existe = false;
        boolean sw = false;
        leerArchivo = new LeerArchivo(escribirArchivo.getPath());
        leerArchivo.abrirArchivo();
        while (!sw) {
            try {
                Corredor corredor = leerArchivo.leerRegistro();
                if ((corredor.getDorsal() == dorsal)) {
                    existe = true;
                    sw = true;
                }
            } catch (IOException e) {
                sw = true;
            }
        }
        leerArchivo.cerrarArchivo();
        return existe;
    }

    public static void consultarRegistro(int dorsal) throws IOException {
        if (escribirArchivo != null && escribirArchivo.exists()) {
            boolean encontrado = false;
            Corredor corredor = null;
            leerArchivo = new LeerArchivo(escribirArchivo.getPath());
            leerArchivo.abrirArchivo();
            while (true) {
                try {
                    corredor = (Corredor) leerArchivo.leerRegistro();
                    if (corredor.getDorsal() == dorsal) {
                        encontrado = true;
                        break;
                    }
                } catch (IOException e) {
                    break;
                }
            }
            if (encontrado && corredor != null) {
                System.out.println(corredor.toString());
            }
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void consultarRegistros() throws IOException {
        if (escribirArchivo != null && escribirArchivo.exists()) {
            boolean sw = false;
            leerArchivo = new LeerArchivo(escribirArchivo.getPath());
            leerArchivo.abrirArchivo();
            while (!sw) {
                try {
                    Corredor corredor = (Corredor) leerArchivo.leerRegistro();
                    System.out.println("---------------------------");
                    System.out.println(corredor.toString());
                    System.out.println("---------------------------");
                } catch (IOException e) {
                    sw = true;
                }
            }
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void borrarRegistro(int dorsal) {
        if (escribirArchivo != null && escribirArchivo.exists()) {
            Predicate<Corredor> condicion = c -> c.getDorsal() == dorsal;
            if (!escribirArchivo.eliminarRegistro(condicion)) {
                System.out.println("No se ha podido borrar el archivo");
            }
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void modificarCorredor(int dorsal, String nombre, double tiempo) {
        if (escribirArchivo != null && escribirArchivo.exists()) {
            Corredor corredor = null;
            Predicate<Corredor> condicion = c -> c.getDorsal() == dorsal;
            String path = escribirArchivo.getPath();
            try {
                corredor = LeerArchivo.recuperaRegistro(condicion, path);
            } catch (IOException e) {
                System.out.println("Error al recuperar el corredor " + e.getMessage());
            }
            if (corredor != null) {
                corredor.setNombre(nombre);
                corredor.setTiempo(tiempo);
                if (escribirArchivo.modificarRegistro(condicion, corredor)) {
                    System.out.println("Registro modificado");
                } else {
                    System.out.println("Registro no modificado");
                }
            }
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }
}
