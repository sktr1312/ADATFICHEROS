package ejemplos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class EjEscrituraSecuencialBytes {
 public static void main(String[] args) throws FileNotFoundException {
 int contLin = 0;
 String lineas[] = {"primera linea",
"segunda linea",
"tercera linea",
"cuarta linea"};
 byte[] s;
 FileOutputStream f=new FileOutputStream("ejemplo1.txt");
 
 try {
     for (String linea : lineas) {
         // copia la cadena en el array de bytes s;
         s = linea.getBytes();
         f.write(s); // graba el array de bytes
         f.write((byte)'\n');
         contLin++; // cuenta
     }
 System.out.println("Grabadas "+contLin+" lineas (exito)");
 f.close();
 }
 catch (IOException e){
 System.out.println("Problema grabación");
 }
//Lectura del archivo
 try{
 FileInputStream fis=new FileInputStream("ejemplo1.txt");
 try{
 int datos;
 datos=fis.read();
 while(datos!=-1)
 {
 System.out.print((char)datos);
datos=fis.read(); 
 }
 f.close();
 }
 catch(IOException e)
 {
 System.out.println("Error en lectura de datos");
 
 }
 }
 catch (FileNotFoundException e) {
 System.out.println("el fichero no se encuentra");
 }
 }
}
