package PracticaMultiprocesoIII;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1Padre {

	public static void main(String[] args) { 
		
		try {
			
			File archivo = new File("dato.txt");
			Scanner sc = new Scanner(archivo);
			
			//Leo la primera linea del archivo
			String numeroStr = sc.nextLine();
			
			///System.out.println(numeroStr);
			
			//Construimos el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", 
					"PracticaMultiprocesoIII.Ejercicio1Hijo", numeroStr);
			
			//Ejecutamos el proceso
			Process proceso = pb.start();
			
			//Esperamos la salida del proceso
			int salida = proceso.waitFor();
			
			///System.out.println(salida);
			
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
			
			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}	//
}	//
