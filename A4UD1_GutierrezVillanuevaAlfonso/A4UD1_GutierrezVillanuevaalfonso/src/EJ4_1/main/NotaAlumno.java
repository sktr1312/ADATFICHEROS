package EJ4_1.main;

import java.io.Serializable;
import java.util.ArrayList;

public class NotaAlumno implements Serializable {

    static final long serialVersionUID = 42L;
    private int numero;
    private ArrayList<NotaModulo> notas;

    public NotaAlumno() {
        this.numero = 0;
        this.notas = new ArrayList<>();
    }

    public NotaAlumno(int numero, ArrayList<NotaModulo> notas) {
        this.numero = numero;
        this.notas = notas;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<NotaModulo> getNotas() {
        return notas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNotas(ArrayList<NotaModulo> notas) {
        this.notas = notas;
    }

    public double getNotaMedia() {
        double media = 0;
        for (NotaModulo nota : notas) {
            media += nota.getNota();
        }
        return media / notas.size();
    }

    @Override
    public String toString() {
        return "NotaAlumno{" + "numero=" + numero + ", notas=" + notas + '}';
    }

}
