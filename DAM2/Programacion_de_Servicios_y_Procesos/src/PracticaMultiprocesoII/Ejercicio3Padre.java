package PracticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3Padre {

	public static void main(String[] args) {
		
		//Le pedimos al usuario una palabra y la guardamos en una variable
		Scanner sc = new Scanner(System.in);
		System.out.println("Escribe un texto:");
		String texto = sc.nextLine();
		sc.close();
		
		try {
			//Creamos el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", 
					"PracticaMultiprocesoII.Ejercicio3Hijo", texto);
	        Process proceso = pb.start();
	        
	        //Creamos un lector para recibir lo que mande el hijo
	        InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
	        BufferedReader br = new BufferedReader(isr);
	        int palindromo = Integer.parseInt(br.readLine());
	        isr.close();
	        br.close();
	        
	        //Esperamos a la salida del proceso hijo
	        int salida = proceso.waitFor();
	        
	        System.out.println("Valor de Salida: " + salida);
	        
	        //Si devuelve 0 es palindromo, si es -1 no lo es
	        if(palindromo == 0)
	        	System.out.println("Es palíndromo");
	        else
	        	System.out.println("NO es palíndromo");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
