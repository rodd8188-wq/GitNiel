package PruebasMultiproceso;

import java.util.Scanner;

public class Padre {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Número: ");
		String numeroStr = sc.nextLine();
		
		//ProcessBuilder pb = new ProcessBuilder("java", "-cp", "Hijo", numeroStr);
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "PruebasMultiproceso.Hijo", numeroStr);
		
		try {
			
			Process proceso = pb.start();
			
			int salida = proceso.waitFor();
			
			System.out.println(salida);
			
			switch (salida) {
			case 0: {
				System.out.println("Tu número es un entero");
				break;
			} case 1: { 
				System.out.println("Tu número es un negativo");
				break;
			} case 255: {	// 256 (-1) 
				System.out.println("No es un número");
				break;
			} default:
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

}
