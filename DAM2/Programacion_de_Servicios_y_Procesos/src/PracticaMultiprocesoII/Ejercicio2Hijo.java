package PracticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio2Hijo {

    public static void main(String[] args) throws Exception {
    	
    	//Crea un lector que lee desde la entrada estandar, el padre envia los datos desde esa entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        double suma = 0;
        String linea;
        
        //Recorre la entrada y la guarda en "linea" mientras que la entrada no este vacia
        while ((linea = br.readLine()) != null) {

            if (linea.equals("*")) {
                break;
            }

            try {
                suma += Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.exit(-1);
            }
        }
        
        System.out.println(suma);
        System.exit(0);
    }
}
