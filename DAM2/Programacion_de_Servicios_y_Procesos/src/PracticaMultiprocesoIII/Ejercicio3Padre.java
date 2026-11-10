package PracticaMultiprocesoIII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Ejercicio3Padre {

	public static void main(String[] args) {
		
		try {
		
			//Abrimos el archivo txt
	    	File archivo = new File("texto.txt");
	    	Scanner sc = new Scanner(archivo);
	    	
	    	String datos = sc.nextLine();
	    	
	    	sc.close();
		
		
			//Creamos el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", 
					"PracticaMultiprocesoIII.Ejercicio3Hijo");
	        Process proceso = pb.start();
	        
	        //Se lo enviamos al hijo
	    	OutputStream os = proceso.getOutputStream();
	    	os.write(datos.getBytes());
	    	os.close();
	        
	        //Creamos un lector para recibir lo que mande el hijo
	        InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
	        BufferedReader br = new BufferedReader(isr);
	        int palindromo = Integer.parseInt(br.readLine());
	        isr.close();
	        br.close();
	        
	        //Esperamos a la salida del proceso hijo
	        int salida = proceso.waitFor();
	        
	        ///System.out.println("Valor de Salida: " + salida)
	        ///System.out.println(salida);
	        
	        //Si devuelve 0 es palindromo, si es -1 no lo es
	        if(salida == 0) {
	        	
	        	//Creamos palindromo.txt
	        	FileWriter fw = new FileWriter("palindromo.txt");
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            String cadena = "La cadena " + datos + " es un palindromo";

	            bw.write(cadena);
	            bw.newLine();
	            bw.write("Valor de Salida: " + salida);

	            bw.close();
	            fw.close();
	        	///System.out.println("Es palíndromo");
	        	
	        } else {
	        	
	        	//Creamos error.txt
	        	FileWriter fw = new FileWriter("error.txt");
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            String cadena = "La cadena " + datos + " no es un palindromo";
	            
	            bw.write(cadena);
	            bw.newLine();
	            bw.write("Valor de Salida: " + salida);

	            bw.close();
	            fw.close();
	        	///System.out.println("NO es palíndromo");
	        }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
