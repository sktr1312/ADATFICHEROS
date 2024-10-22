package EJ3_A3UD1;

import java.io.IOException;
import utiles.Utiles;

public class EJ3_A3UD1 {
    public static void main(String[] args) throws IOException {
        System.out.println("FICHERO NUM1.DAT");
        Utiles.pintarArchivo("Num1.dat");
        System.out.println("FICHERO NUM2.DAT");
        Utiles.pintarArchivo("Num2.dat");
        System.out.println("FICHERO MEZCLA.DAT");
        Utiles.mezclaFicheros("Num1.dat", "Num2.dat");
        Utiles.pintarArchivo("Mezcla.dat");
       }
       
}
