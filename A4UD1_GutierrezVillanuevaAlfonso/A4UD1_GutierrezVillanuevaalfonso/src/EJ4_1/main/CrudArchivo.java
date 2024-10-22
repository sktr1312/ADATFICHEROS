package EJ4_1.main;

import EJ4_1.utiles.EscribirArchivo;
import EJ4_1.utiles.EscribirTxt;
import EJ4_1.utiles.LeerArchivo;
import EJ4_1.utiles.LeerEscribirArchivo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;

public class CrudArchivo {

    private final static String ARCHIVO_RAF = "src/files/alumnos.dat";
    private final static String ARCHIVO_SECUENCIAL = "src/files/NotasAlumnos.dat";
    private final static String ARCHIVO_TXT = "src/files/alumnosNotas.txt";
    private final static LeerEscribirArchivo lea = new LeerEscribirArchivo(ARCHIVO_RAF);
    private final static LeerArchivo leerArchivo = new LeerArchivo(ARCHIVO_SECUENCIAL);
    private final static EscribirArchivo escribirArchivo = new EscribirArchivo(ARCHIVO_SECUENCIAL);
    private final static EscribirTxt escribirTxt = new EscribirTxt(ARCHIVO_TXT);

    // Arcivo de acceso aleatorio
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

    // private int numero;
    // private Nombre nombre;
    // Date fechaNac;
    // ArrayList<String> telefono;
    // boolean borrado;
    public static boolean anhadirRegistrosAlumno(Nombre nombre, Date fechaNac, ArrayList<String> telefono) {
        boolean added = false;
        lea.abrirArchivo();
        if (!comprobarAlumnoNombre(nombre)) {
            lea.escribirRegistro(lea.numRegistros() + 1, nombre.toString(), fechaNac.getTime(), telefono.size(), telefono, false);
            added = true;
        } else {
            System.out.println("El alumno ya existe");
        }
        lea.cerrarArchivo();
        return added;
    }

    private static boolean comprobarAlumnoNombre(Nombre nombre) {
        boolean existe = false;
        for (int i = 1; i <= lea.numRegistros(); i++) {
            Alumno alumno = lea.recuperarRegistro(i);
            if (alumno.getNombre().equals(nombre)) {
                existe = true;
            }

        }
        return existe;
    }

    public static Alumno recuperaAlumno(int numAlumno) throws IOException {
        Alumno alumno;
        lea.abrirArchivo();
        alumno = lea.recuperarRegistro(numAlumno);
        lea.cerrarArchivo();
        return alumno;

    }

    public static void consultarRegistroAlumno(int numAlumno) throws IOException {
        Alumno alumno;
        lea.abrirArchivo();
        alumno = lea.recuperarRegistro(numAlumno);
        lea.cerrarArchivo();
        if (alumno != null) {
            System.out.println(alumno.toString());
        } else {
            System.out.println("No se ha encontrado el corredor con dorsal " + numAlumno);
        }
    }

    public static void consultarRegistrosAlumno() throws IOException {
        Alumno alumno;
        lea.abrirArchivo();
        for (int i = 1; i <= lea.numRegistros(); i++) {
            alumno = lea.recuperarRegistro(i);
            if (!alumno.isBorrado()) {
                System.out.println(alumno.toString());
            }
        }
        lea.cerrarArchivo();
    }

    public static void borrarRegistroAlumno(int numAlumno) {
        Alumno alumno;
        lea.abrirArchivo();
        alumno = lea.recuperarRegistro(numAlumno);
        if (alumno != null) {
            alumno.setBorrado(true);
            lea.escribirRegistro(numAlumno, alumno.getNombre().toString(), alumno.getFechaNac().getTime(), alumno.getTelefono().size(), alumno.getTelefono(), false);
        } else {
            System.out.println("No se ha encontrado el alumno con dorsal " + numAlumno);
        }
        lea.cerrarArchivo();
    }

    public static void modificarAlumno(int numAlumno, Nombre nombre, Date fechaNac, ArrayList<String> telefono) {
        Alumno alumno;
        lea.abrirArchivo();
        alumno = lea.recuperarRegistro(numAlumno);
        if (alumno != null) {
            alumno.setNombre(nombre);
            alumno.setFechaNac(fechaNac);
            alumno.setTelefono(telefono);
            lea.escribirRegistro(numAlumno, alumno.getNombre().toString(), alumno.getFechaNac().getTime(), alumno.getTelefono().size(), alumno.getTelefono(), false);
        } else {
            System.out.println("No se ha encontrado el alumno con dorsal " + numAlumno);
        }
        lea.cerrarArchivo();
    }

    public static int numRegistros() {
        lea.abrirArchivo();
        int numRex = lea.numRegistros();
        lea.cerrarArchivo();
        return numRex;
    }

    // Archivo secuencial
    public static void anhadirRegistrosNotasAlumno(NotaAlumno notaAlumno) {
        if (comprobarAlumno(notaAlumno.getNumero())) {
            escribirArchivo.abrirArchivo();
            escribirArchivo.anhadirObjeto(notaAlumno);
            escribirArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static NotaAlumno recuperaNotaAlumno(int numAlumno) throws IOException {
        NotaAlumno notaAlumno = null;
        boolean sw = false;
        if (comprobarAlumno(numAlumno)) {
            leerArchivo.abrirArchivo();
            while (!sw) {
                try {
                    notaAlumno = (NotaAlumno) leerArchivo.leerRegistro();
                    if (notaAlumno.getNumero() == numAlumno) {
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
        return notaAlumno;
    }

    public static boolean comprobarAlumno(int numAlumno) {
        boolean existe = false;
        boolean sw = false;
        lea.abrirArchivo();
        if (lea.numRegistros() == 0 || numAlumno > lea.numRegistros() || numAlumno < 1) {
            existe = false;
        } else {

            while (!sw) {
                Alumno alumno = lea.recuperarRegistro(numAlumno);
                if (alumno.getNumero() == numAlumno) {
                    existe = true;
                    sw = true;
                }
            }

        }
        lea.cerrarArchivo();
        return existe;
    }

    public static void consultarRegistroNotasAlumno(int numAlumno) throws IOException {
        if (escribirArchivo.exists()) {
            boolean encontrado = false;
            boolean sw = false;
            NotaAlumno notaAlumno = null;
            leerArchivo.abrirArchivo();
            while (!sw) {
                try {
                    notaAlumno = (NotaAlumno) leerArchivo.leerRegistro();
                    if (notaAlumno.getNumero() == numAlumno) {
                        encontrado = true;
                        sw = true;
                    }
                } catch (IOException e) {
                    sw = true;
                }
            }
            if (encontrado && notaAlumno != null) {
                System.out.println(notaAlumno.toString());
            }
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void consultarRegistrosNotasAlumno() throws IOException {
        if (escribirArchivo.exists()) {
            boolean sw = false;
            leerArchivo.abrirArchivo();
            while (!sw) {
                try {
                    NotaAlumno notaAlumno = (NotaAlumno) leerArchivo.leerRegistro();
                    System.out.println("---------------------------");
                    System.out.println(notaAlumno.toString());
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

    public static void borrarRegistroNotasAlumno(int numAlumno) {
        if (escribirArchivo.exists()) {
            Predicate<NotaAlumno> condicion = c -> c.getNumero() == numAlumno;
            if (!escribirArchivo.eliminarRegistro(condicion)) {
                System.out.println("No se ha podido borrar el registro");
            }
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void modificarNotaAlumno(int numAlumno, NotaAlumno notaAlumno) {
        if (escribirArchivo.exists() && comprobarAlumno(numAlumno)) {
            NotaAlumno corredor = null;
            Predicate<NotaAlumno> condicion = c -> c.getNumero() == numAlumno;
            try {
                notaAlumno = leerArchivo.recuperaRegistro(condicion);
            } catch (IOException e) {
                System.out.println("Error al recuperar las notas del alumno " + e.getMessage());
            }
            if (notaAlumno != null) {
                notaAlumno.setNotas(notaAlumno.getNotas());
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

    public static void consultarRegistrosNotasPorAlumno() {
        if (leerArchivo.exists()) {
            leerArchivo.abrirArchivo();
            lea.abrirArchivo();
            boolean sw = false;
            while (!sw) {
                try {
                    NotaAlumno notaAlumno = (NotaAlumno) leerArchivo.leerRegistro();
                    Alumno alumno = lea.recuperarRegistro(notaAlumno.getNumero());
                    while (notaAlumno.getNumero() != alumno.getNumero()) {
                        notaAlumno = (NotaAlumno) leerArchivo.leerRegistro();
                    }

                    System.out.println(alumno.toString());
                    System.out.println(notaAlumno.toString());

                } catch (IOException e) {
                    sw = true;
                }
            }
            lea.cerrarArchivo();
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void consultarRegistroNotasPorAlumno(int numAlumno) {
        if (leerArchivo.exists()) {
            leerArchivo.abrirArchivo();
            lea.abrirArchivo();
            try {
                Predicate<NotaAlumno> condicion = c -> c.getNumero() == numAlumno;
                NotaAlumno notaAlumno = (NotaAlumno) leerArchivo.recuperaRegistro(condicion);
                Alumno alumno = lea.recuperarRegistro(numAlumno);
                System.out.println(alumno.toString());
                System.out.println(notaAlumno.toString());

            } catch (IOException e) {
                System.out.println("Error al leer el archivo " + e.getMessage());
            }
            lea.cerrarArchivo();
            leerArchivo.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }

    public static void addTelefonoAlumno(int numAlumno, String telefono) {
        lea.abrirArchivo();
        Alumno alumno = lea.recuperarRegistro(numAlumno);
        if (alumno != null) {

            if (alumno.getTelefono().contains(telefono)) {
                System.out.println("El telefono ya existe quiere borralo? (s/n)");
                String respuesta = new Scanner(System.in).nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    alumno.getTelefono().remove(telefono);
                }
            } else {
                alumno.getTelefono().add(telefono);
            }
            lea.escribirRegistro(numAlumno, alumno.getNombre().toString(), alumno.getFechaNac().getTime(), alumno.getTelefono().size(), alumno.getTelefono(), false);
        } else {
            System.out.println("No se ha encontrado el alumno con dorsal " + numAlumno);
        }
        lea.cerrarArchivo();
    }

    // archivo txt
    public static void addLogAlumnos() throws IOException {
        if (lea.exists() && leerArchivo.exists()) {
            escribirTxt.abrirArchivo();
            lea.abrirArchivo();
            leerArchivo.abrirArchivo();
            escribirTxt.escribirCabecera("----------------------DATOS ALUMNOS ----------------------------------------");
            for (int i = 1; i <= lea.numRegistros(); i++) {
                Alumno alumno = lea.recuperarRegistro(i);
                Predicate<NotaAlumno> condicion = c -> c.getNumero() == alumno.getNumero();
                NotaAlumno notaAlumno = leerArchivo.recuperaRegistro(condicion);
                escribirTxt.escribirLinea("ALUMNO NUMERO: " + alumno.getNumero());
                escribirTxt.escribirLinea("NOMBRE: " + alumno.getNombre().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                escribirTxt.escribirLinea("FECHA NACIMIENTO; " + sdf.format(alumno.getFechaNac()) + " EDAD: " + alumno.getEdad());
                escribirTxt.escribirSinSalto("TELEFONO (S): ");
                for (String telefono : alumno.getTelefono()) {
                    escribirTxt.escribirSinSalto(telefono + " ");
                }
                escribirTxt.escribirLinea("");
                escribirTxt.escribirLinea("MODULO\t\t\t\t\tNOTA");
                escribirTxt.escribirLinea("---------------------------------------------------------------------------------------------------------------");
                for (NotaModulo notaModulo : notaAlumno.getNotas()) {
                    escribirTxt.escribirLinea(notaModulo.getAsignatura() + "\s\t\t\t\t\t" + notaModulo.getNota());
                }
                escribirTxt.escribirLinea("……………………………");

                escribirTxt.escribirLinea("NOTA MEDIA " + "\t\t\t\t" + String.format("%.2f", notaAlumno.getNotaMedia()));
                escribirTxt.escribirLinea("………………………………");
                escribirTxt.escribirLinea("…………………………");

            }
            escribirTxt.escribirLinea("TOTAL DE ALUMNOS ………………………….." + lea.numRegistros());
            escribirTxt.cerrarArchivo();
            lea.cerrarArchivo();
        } else {
            System.out.println("No se ha creado el archivo");
        }
    }
//----------------------DATOS ALUMNOS ----------------------------------------
// ALUMNO NUMERO: x
// NOMBRE: XXXXXXX XXXXXXX XXXXXXX ( nombre con apellidos)
// FECHA NACIMIENTO; XX/XX/XXXX EDAD: X
// TELEFONO (S):XXXXXXXX XXXXXXX
// MODULO                       NOTA
// ---------------------------------------------------------------------------------------------------------------
// XXXXXXXXXXXXXX                X
// XXXXXXXXXXXXXX                X
// ……………………………
// NOTA MEDIA X
// …………………….
// ………………….
// ------------------------------------------------------------------------------------------------------------------------
// ALUMNO NUMERO: X
// NOMBRE: XXXXXXX XXXXXXX XXXXXXX ( nombre con apellidos)
// FECHA NACIMIENTO; XX/XX/XXXX EDAD:x
// MODULO NOTA
// ---------------------------------------------------------------------------------------------------------------
// XXXXXXXXXXXXXX X
// XXXXXXXXXXXXXX X
// ……………………………
// NOTA MEDIA X
// ------------------------------------------------------------------------------
// TOTAL DE ALUMNOS …………………………..X

}
