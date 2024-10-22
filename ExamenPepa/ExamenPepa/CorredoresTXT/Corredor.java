import java.io.Serializable;

public class Corredor implements Serializable {
    private String nombre;
    private int dorsal;
    private double tiempo;

    public Corredor(String nombre, int dorsal, double tiempo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public double getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Dorsal: " + dorsal + ", Tiempo: " + tiempo;
    }
}
