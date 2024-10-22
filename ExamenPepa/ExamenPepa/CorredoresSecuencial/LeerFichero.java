

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerFichero extends Archivo {
    private ObjectInputStream ois;

    
    public LeerFichero(String f) {
            super(f);
        }
    
        @Override
        public void abrirArchivo() {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                System.out.println("Archivo abierto correctamente.");
            } catch (IOException e) {

            }
        }
    
        @Override
        public void cerrarArchivo() {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo.");
                }
            }
        }

    public boolean corredorExistente(String nombre) {
        if (ois != null) {
            while (true) {
                try {
                    Corredor corredor = (Corredor) ois.readObject();
                    if (corredor.getNombre().equals(nombre)) {
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dorsalExistente(int num) {
        if (ois != null) {
            while (true) {
                try {
                    Corredor corredor = (Corredor) ois.readObject();
                
                    if (corredor.getDorsal() == num) {
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return true;
                }
            }
        }
        return false;

    }


    public void mostrarTodos(){
            try {
                while (true) {
                    Corredor corredor = (Corredor) ois.readObject();
                    System.out.println(corredor.toString());
                }
            } catch (EOFException e) {
                // Si llegamos al final del archivo, salimos del bucle
                System.out.println("Fin del archivo.");
            } catch (ClassNotFoundException | IOException e) {
                // Manejo de otras excepciones como problemas de lectura/escritura
                e.printStackTrace();
            }
        }

    

    public void mostrarPorDorsal(int numDorsal){
        try {
            while (true) {
                Corredor corredor = (Corredor) ois.readObject();
                if (corredor.getDorsal() == numDorsal) {
                    System.out.println(corredor.toString());
                }
            }
        } catch (EOFException e) {
            // Si llegamos al final del archivo, salimos del bucle
            System.out.println("Fin del archivo.");
        } catch (ClassNotFoundException | IOException e) {
            // Manejo de otras excepciones como problemas de lectura/escritura
            e.printStackTrace();
        }
    }


    public int ultimoObject() {
        int ultimoDorsal = 0;
        if (ois != null) {
            while (true) {
                try {
                    Corredor corredor = (Corredor) ois.readObject();
                    ultimoDorsal = corredor.getDorsal();
                } catch (IOException e) {
                    return ultimoDorsal;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return 0;
                }
            }
        }
        return ultimoDorsal;

    }

    public Corredor buscarCorredor(int numDorsal) throws IOException {
        Corredor corredorTmp = null; 
        if (ois != null) {
            boolean continuar = true;
            while (continuar) {
                try {
                    corredorTmp = (Corredor) ois.readObject();
                    continuar = false;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                }
            }
        }
        return corredorTmp.getDorsal() == numDorsal ? corredorTmp : null; 
    }

    public Corredor leerObjeto(){
        Corredor corredor = null;
        try {
            corredor = (Corredor) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return corredor;
    }
}
