package EJ4_1.main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Alumno implements Serializable {

    private int numero;
    private Nombre nombre;
    Date fechaNac;
    ArrayList<String> telefono;
    boolean borrado;

    public Alumno() {
        this.numero = 0;
        this.nombre = new Nombre();
        this.fechaNac = new Date();
        this.telefono = new ArrayList<>();
        this.borrado = false;
    }

    public Alumno(int numero, Nombre nombre, Date fechaNac, ArrayList<String> telefono, boolean borrado) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.borrado = borrado;
    }

    public int getNumero() {
        return numero;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public ArrayList<String> getTelefono() {
        return telefono;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setFechaNac(Long fechaNac) {
        this.fechaNac = new Date(fechaNac);
    }

    public void setTelefono(ArrayList<String> telefono) {
        this.telefono = telefono;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Alumno{" + "numero=" + numero + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", telefono=" + telefono + ", borrado=" + borrado + '}';
    }

    int getEdad() {
        if (fechaNac == null) {
            throw new IllegalStateException("La fecha de nacimiento no puede ser null");
        }

        LocalDate fechaNacimiento = fechaNac.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fechaActual = LocalDate.now();

        // Validar que la fecha de nacimiento no sea futura
        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalStateException("La fecha de nacimiento no puede ser futura");
        }

        Period periodo = Period.between(fechaNacimiento, fechaActual);

        // Validar edad razonable para un estudiante
        int edad = periodo.getYears();
        if (edad > 120) {
            throw new IllegalStateException("La edad calculada no es razonable: " + edad);
        }

        return edad;
    }

}
