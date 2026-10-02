package PruebasMultiproceso;

import java.util.Scanner;

public class Padre {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String numeroStr = sc.nextLine();
		
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "Hijo", numeroStr);
		
		try {
			
			Process proceso = pb.start();
			
			int salida = proceso.waitFor();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

}
