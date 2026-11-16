package genft;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class SubApGenFT {

	// Fichero de bloqueo
	public static final String FICHERO_BLOQUEO = "transferencias.bloqueo";

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in, "UTF-8");

		// Leemos la ruta
		String ruta = teclado.nextLine();
		
		// Leemos el nombre del fichero
		String nombre = teclado.nextLine();
		
		// Leemos el numero de transferencias
		int numTransferencias = Integer.parseInt(teclado.nextLine());

		Random aleatorio = new Random();

		try {
			
			// Esperamos entre 1 y 5 segundos
			Thread.sleep((aleatorio.nextInt(5) + 1) * 1000);
		} catch (InterruptedException e) {
			
			System.err.println("Me han interrumpido mientras esperaba");
		}

		// Sacamos un numero aleatorio entre 1 y 100
		int numero = aleatorio.nextInt(100) + 1;

		// Si el numero es menor o igual que 30
		if (numero <= 30) {
			
			System.err.println("El fichero se genero con errores (aleatorio = " + numero + ")");
			// Terminamos el programa con el codigo de error 1
			System.exit(1);
		}

		// Creamos un fichero
		File ficheroTransferencias = new File(ruta + nombre);
		
		// Creamos otro fichero
		File ficheroBloqueo = new File(ruta + FICHERO_BLOQUEO);

		// Si no existe ya
		if (!ficheroTransferencias.exists()) {
			try {
				
				// Creamos la carpeta de la ruta
				new File(ruta).mkdirs();

				// Creamos el fichero de bloqueo
				ficheroBloqueo.createNewFile();

				// Abrimos el fichero
				PrintWriter escritor = new PrintWriter(ficheroTransferencias, "UTF-8");

				// Repetimos las transferencias
				for (int i = 0; i < numTransferencias; i++) {

					// 1 o 2
					int primerDigito = aleatorio.nextInt(2) + 1;
					
					// Numero entre 10000000 y 99999999
					int otrosDigitos = aleatorio.nextInt(90000000) + 10000000;
					
					// Unimos los cachos
					String cuenta = "" + primerDigito + otrosDigitos;

					// Entre 1500 y 3000
					double importe = 1500 + aleatorio.nextDouble() * 1500;
					
					// Redondeamos a 2 decimales
					importe = Math.round(importe * 100) / 100.0;

					// Escribimos la linea en el ficheroo
					escritor.println(cuenta + ";" + importe);

					// Esperamos 10 milisegundos entre una transferencia y la de despues
					Thread.sleep(10);
				}
				
				// Lo cerramos
				escritor.close();

				// Borramos el fichero de bloqueo
				ficheroBloqueo.delete();

			} catch (IOException e) {
				
				// Si hay un error de entrada/salida lo mostramos
				System.err.println("Error de entrada o salida: " + e.getMessage());
				
				// Codigo de error 2
				System.exit(2);
				
			} catch (InterruptedException e) {
				
				// Si nos interrumpen durante el sleep de 10 ms
				System.err.println("Me han interrumpido");
				
				// Lo tratamos tambien como error de entrada/salida
				System.exit(2);
			}
		}

		// Si hemos llegado hasta aqui todo ha ido bien
		System.out.println("Fichero de transferencias generado correctamente");
		System.exit(0);
	}
}
