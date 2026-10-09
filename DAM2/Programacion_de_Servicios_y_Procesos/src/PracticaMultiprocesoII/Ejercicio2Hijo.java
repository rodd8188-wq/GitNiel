package PracticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio2Hijo {

	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		
		Scanner sc = new Scanner(System.in);
		int sumita = 0;

		try {
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				if (!linea.equals("*")) {
					int numero = Integer.parseInt(linea);
					sumita += numero;

					System.out.printf("Escrito %s", numero);
				}
			}

			System.out.println("Suma: " + sumita);
		} catch (Exception e) {
			System.exit(-1);
		}

	}// main
}// class
