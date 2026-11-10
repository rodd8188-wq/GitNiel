package PracticaMultiprocesoIII;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Ejercicio2Padre {

    public static void main(String[] args) throws Exception {

        //Abrimos el archivo txt
    	File archivo = new File("suma.txt");
    	Scanner sc = new Scanner(archivo);
    	
    	String datos = "";
    	
    	//Leemos el archivo linea por linea
    	while(sc.hasNextLine()) {
    		String linea = sc.nextLine();
    		datos += linea + "\n";
    		System.out.println(Double.valueOf(linea));
    	}
    	
    	sc.close();
    	
    	//Creamos el proceso
    	ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", 
    			"PracticaMultiprocesoIII.Ejercicio2Hijo");
    	
    	Process proceso = pb.start();
    	
    	//Se lo enviamos al hijo
    	OutputStream os = proceso.getOutputStream();
    	os.write(datos.getBytes());
    	os.close();
    	
    	//Leemos lo que devuelve el hijo
    	InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
    	BufferedReader br = new BufferedReader(isr);
    	
    	String salida;
    	
    	while((salida = br.readLine()) != null ) {
    		System.out.println("Suma: " + salida);
    	}
    	
    	if(proceso.waitFor() != 0)
    		System.out.println("Se ha introducido una cadena");
    	
    	isr.close();
        br.close();
    }
}
