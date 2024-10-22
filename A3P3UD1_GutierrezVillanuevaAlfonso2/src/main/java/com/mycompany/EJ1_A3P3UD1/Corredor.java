package com.mycompany.EJ1_A3P3UD1;

import java.io.Serializable;

public class Corredor implements Serializable, Comparable<Corredor> {

    private String nombre;
    final private int DORSAL;
    private double tiempo; // Tiempo en minutos

    public Corredor(String nombre, int dorsal, double tiempo) {
        this.nombre = nombre;
        this.DORSAL = dorsal;
        this.tiempo = tiempo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getDorsal() {
        return DORSAL;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "[Nombre=" + nombre + ", Dorsal=" + DORSAL + ", Tiempo=" + tiempo + " minutos]";
    }

    // hashCode, equals y compareTo
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.DORSAL;
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
        return this.DORSAL == other.DORSAL;
    }

    @Override
    public int compareTo(Corredor o) {
        return this.DORSAL - o.DORSAL;
    }
}
