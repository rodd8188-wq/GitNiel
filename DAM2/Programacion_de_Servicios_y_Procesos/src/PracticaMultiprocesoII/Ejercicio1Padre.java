package PracticaMultiprocesoII;

import java.util.Scanner;

public class Ejercicio1Padre {
	
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Número: ");
		String numeroStr = sc.nextLine();
		
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "PracticaMultiprocesoII.Ejercicio1Hijo", numeroStr);
		
		try {
			
			Process proceso = pb.start();
			
			int salida = proceso.waitFor();
			
			//System.out.println(salida);
			
			switch (salida) {
			case 255: {		//	256 (-1)
				System.out.println("El argumento esta vacío");
				break;
			} case 254: {		//	256 (-2)
				System.out.println("El argumento no es un entero");
				break;
			} case 253: {		//	256 (-3)
				System.out.println("El argumento es un entero positivo");
				break;
			} case 0: {		//	0
				System.out.println("El argumento es un entero negativo");
				break;
			}
			default:
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
}
