package EJ4_1.main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ej4_1 {

    public static void main(String[] args) {
        // anhadirAlumnos(sc);
        // anhadirAlumnos(sc);
        // consultarAlumnos();
        try (Scanner sc = new Scanner(System.in)) {
            // anhadirAlumnos(sc);
            // anhadirAlumnos(sc);
            // consultarAlumnos();
            // addNotasAlumno(sc);
            // addNotasAlumno(sc);
            // consultarNotasAlumnos();
            // consultarNotasPorAlumnos();
            // consultarNotasAlumno(sc);
            // addTelefono(sc);
            addLogAlumnos();
        }
    }

    private static void addLogAlumnos() {
            try {
                CrudArchivo.addLogAlumnos();
            } catch (IOException e) {
                System.out.println("Error al añadir el log de alumnos " + e.getMessage());
            }
    }
        
    private static void addTelefono(Scanner sc) {
    

        consultarAlumnos();
        System.out.println("Introduzca el numero del alumno");
        int numAlumno = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el telefono a añadir");
        String telefono = sc.nextLine();
        CrudArchivo.addTelefonoAlumno(numAlumno, telefono);
    }

    private static void consultarNotasAlumno(Scanner sc) {
        consultarAlumnos();
        System.out.println("Introduzca el numero del alumno");
        int numAlumno = sc.nextInt();
        CrudArchivo.consultarRegistroNotasPorAlumno(numAlumno);
    }

    private static void consultarNotasPorAlumnos() {
        CrudArchivo.consultarRegistrosNotasPorAlumno();
    }

    private static void consultarNotasAlumnos() {

        try {
            CrudArchivo.consultarRegistrosNotasAlumno();
        } catch (IOException e) {
            System.out.println("Error al consultar los registros " + e.getMessage());
        }
    }

    private static void addNotasAlumno(Scanner sc) {
        int numAlumno;
        String nombreModulo;
        NotaAlumno notaAlumno;
        ArrayList<NotaModulo> notasModulos = new ArrayList<>();
        consultarAlumnos();
        System.out.println("Introduzca el numero del alumno");
        numAlumno = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el nombre del modulo: * para terminar");
        nombreModulo = sc.nextLine();
        while (!nombreModulo.equals("*")) {
            System.out.println("Introduzca la nota del modulo");
            int nota = sc.nextInt();
            sc.nextLine();
            notasModulos.add(new NotaModulo(nombreModulo, nota));
            System.out.println("Introduzca el nombre del modulo: * para terminar");
            nombreModulo = sc.nextLine();
        }
        notaAlumno = new NotaAlumno(numAlumno, notasModulos);
        CrudArchivo.anhadirRegistrosNotasAlumno(notaAlumno);

    }

    private static void anhadirAlumnos(Scanner scanner) {
        String nombre;
        String apellido;
        Date fechaNac;
        ArrayList<String> telefonos = new ArrayList<>();
        try {
            do {
                System.out.println("Introduzca el nombre del alumno");
                nombre = scanner.nextLine();
                while (nombre.isEmpty()) {
                    System.out.println("Introduzca el nombre del alumno");
                    nombre = scanner.nextLine();
                }
                System.out.println("Introduzca el apellido del alumno con formato: apellido1 apellido2");
                apellido = scanner.nextLine();
                while (apellido.isEmpty() || apellido.split(" ").length != 2) {
                    System.out.println("Introduzca el apellido del alumno con formato: apellido1 apellido2");
                    apellido = scanner.nextLine();
                }

                System.out.println("Introduzca la fecha de nacimiento del alumno: dd/MM/yyyy");
                String strFechaNac = scanner.nextLine();
                while (strFechaNac.isEmpty() || !strFechaNac.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    System.out.println("Introduzca la fecha de nacimiento del alumno: dd/MM/yyyy");
                    strFechaNac = scanner.nextLine();
                }
                fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaNac);
                // primer numero de telefono obligatorio
                System.out.println("Introduzca el numero de telefono del alumno");
                String telefono = scanner.nextLine();
                while (!telefono.isEmpty() && !telefono.matches("\\d{9}")) {
                    System.out.println("El primer numero es obligatorio y debe tener 9 digitos: ");
                    telefono = scanner.nextLine();
                }
                telefonos.add(telefono);
                // resto de telefonos opcionales
                while (!telefono.equals("*") && telefonos.size() < 3) {
                    System.out.println("Introduzca el telefono del alumno: * para terminar");
                    telefono = scanner.nextLine();
                    while (!telefono.isEmpty() && !telefono.matches("\\d{9}|\\*") && telefonos.contains(telefono)) {
                        System.out.println("Introduzca el numero de telefono del alumno: * para terminar");
                        telefono = scanner.nextLine();
                    }
                    if (!telefono.equals("*")) {
                        telefonos.add(telefono);
                    }
                }

                System.out.println("Telefonos introducidos: " + telefonos.size());
            } while (!CrudArchivo.anhadirRegistrosAlumno(new Nombre(nombre, apellido.split(" ")[0], apellido.split(" ")[1]), fechaNac, telefonos));
        } catch (ParseException e) {
            System.out.println("Error al introducir los datos " + e.getMessage());
        }
    }

    private static void consultarAlumnos() {
        try {
            CrudArchivo.consultarRegistrosAlumno();
        } catch (IOException e) {
            System.out.println("Error al consultar los registros " + e.getMessage());
        }
    }
}
