package EJ1_A3P4UD1;

import java.io.Serializable;

public final class Corredor implements Serializable, Comparable<Corredor> {

    private String nombre;
    private int dorsal;
    private double tiempo; // Tiempo en minutos
    private boolean borrado;

    public Corredor() {
    }

    public Corredor(String nombre, int dorsal, double tiempo) {
        setNombre(nombre);
        this.dorsal = dorsal;
        this.tiempo = tiempo;
        borrado = false;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 48) {
            nombre = nombre.substring(0, 48);
        }
        this.nombre = nombre;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    @Override
    public String toString() {
        return "[Nombre=" + nombre + ", Dorsal=" + dorsal + ", Tiempo=" + tiempo + " minutos]";
    }

    // hashCode, equals y compareTo
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.dorsal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Corredor other = (Corredor) obj;
        return this.dorsal == other.dorsal;
    }

    @Override
    public int compareTo(Corredor o) {
        return this.dorsal - o.dorsal;
    }
}
