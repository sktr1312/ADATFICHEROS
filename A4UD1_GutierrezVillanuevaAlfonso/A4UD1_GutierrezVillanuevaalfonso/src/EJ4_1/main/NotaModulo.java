package EJ4_1.main;

public class NotaModulo implements java.io.Serializable {
    String asignatura;
    int nota;

    public NotaModulo() {
        this.asignatura = "";
        this.nota = 0;
    }

    public NotaModulo(String asignatura, int nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public int getNota() {
        return nota;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return  asignatura + ", " + nota;
    }
    
}
