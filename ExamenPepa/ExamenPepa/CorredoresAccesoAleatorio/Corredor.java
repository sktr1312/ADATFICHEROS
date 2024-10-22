/*@Author : Borja Otero Ferreiera  */
package ud1a3_p4;
import java.io.Serializable;

public class Corredor implements Serializable {
    private String nombre; 
    private int dorsal; 
    private double tiempo;
    boolean borrado; 
    
    public Corredor() {
         
    }

    public Corredor(String nombre, int dorsal, double tiempo) {
        this.nombre = nombre; 
        this.dorsal = dorsal; 
        this.tiempo = tiempo; 
        this.borrado = false; 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Corredor buscarCorredor(int dorsal) {
        return null;
    }

    @Override
    public String toString() {
        return  "   Nombre: " + nombre + 
                "   Dorsal: " + dorsal + 
                "   Tiempo: " + tiempo ;
    }
}
