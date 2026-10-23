
package PracticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Ejercicio2Padre {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ///StringBuilder datos = new StringBuilder();
        String linea;
        String datos = "";

        do {
        	System.out.println("Escribe un número:");
            linea = sc.nextLine();
            
            if(!linea.equals("*"))
            	//Añade la linea leida a "datos" junto a un /n
                ///datos.append(linea).append("\n");
                datos += linea + "\n";
            
        //Mientras que la linea leida no sea igual a *
        } while (!linea.equals("*"));
        
        ///System.out.println(datos);
        
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "PracticaMultiprocesoII.Ejercicio2Hijo");
        Process proceso = pb.start();
        
        //Se le asigna el flujo por donde el padre se puede comunicar con el hijo
        OutputStream os = proceso.getOutputStream();
        
        //Envia los datos al hijo en una cadena de bytes
        ///os.write(datos.toString().getBytes());
        os.write(datos.getBytes());
        
        //Cerramos para que el hijo puede detectar el final de la entrada
        os.close();
        
        //Creamos un lector para recibir lo que mande el hijo
        InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        
        //Leemos la salida del hijo linea por linea
        String resultado;
        while ((resultado = br.readLine()) != null) {
            System.out.println("Suma: " + resultado);
        }
        
        //El waitFor() devuelve la salida en codigo del final del hijo
        if (proceso.waitFor() != 0) {
            System.out.println("Se ha introducido una cadena.");
        }
        
        sc.close();
    }
}
