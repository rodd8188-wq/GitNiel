package PracticaMultiprocesoII;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ejercicio2Padre {

	public static void main(String[] args) throws IOException {
		System.out.println("Inicio programa...");
		
		Scanner sc = new Scanner(System.in);
		String numeroStr = "";
		String numeros = "";
		
		while(!numeroStr.equals("*")) {
			System.out.println("Escribe un número: ");
			numeroStr = sc.nextLine();
			numeros += numeroStr + "\n";
			
		}

		ProcessBuilder pb = new ProcessBuilder(
				"java", "-cp", "bin", "PracticaMultiprocesoII.Ejercicio2Hijo", numeros);
		
		Process p = pb.start();
		int exitVal = -1;
		
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (exitVal == 0) {
			// obtener la salida devuelta por el proceso
			try {
				InputStream is = p.getInputStream();
				Scanner sc2 = new Scanner(is);
				String salida = sc2.nextLine();
				System.out.println(salida);
				
				while (sc2.hasNext() == true) {
					salida = sc2.nextLine();
					System.out.println(salida);
				}
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// main
}// class
