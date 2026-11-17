package apprinc;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ApPrinc {
	
	public static final String RUTA_POR_DEFECTO = "C:\\Users\\rumbl\\Downloads\\pago_nominas_Daniel\\nominas\\";
	public static final String FICHERO_BLOQUEO = "transferencias.bloqueo";
	
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		// Pedimos el nombre del fichero
		System.out.println("Dime el nombre del fichero a generar:");
		// Leemos lo que escribe el usuario
		String nombre = teclado.nextLine();
		// Si no escribio nada (cadena vacia) usamos el valor por defecto
		if (nombre.trim().isEmpty()) {
			nombre = "transfer_data";
		}

		// Pedimos la ruta
		System.out.println("Dime la ruta donde lo guardo (acábalo en barra)");
		// Leemos la ruta
		String ruta = teclado.nextLine();
		// Si no escribio nada usamos la ruta por defecto
		if (ruta.trim().isEmpty()) {
			ruta = RUTA_POR_DEFECTO;
		}

		// Pedimos el numero de transferencias
		System.out.println("Dime el número de transferencias a generar");
		
		// Leemos el texto que escribe el usuario
		String txtTransferencias = teclado.nextLine();
		
		// Pedimos el numero de hilos
		System.out.println("Dime el número de hilos con el que procesar el fichero");
		
		// Leemos el texto que escribe el usuario
		String txtoHilos = teclado.nextLine();

		// Variables donde guardaremos los numeros ya convertidos
		int numTransferencias = 0;
		int numHilos = 0;
		try {
			
			// Convertimos los textos a numeros enteros
			numTransferencias = Integer.parseInt(txtTransferencias.trim());
			numHilos = Integer.parseInt(txtoHilos.trim());
		} catch (NumberFormatException e) {
			
			// Si escribio letras en vez de numeros, avisamos y terminamos
			System.out.println("Error: debes escribir números enteros.");
		}

		// Comprobamos que los dos numeros sean mayores que 0
		if (numTransferencias <= 0 || numHilos <= 0) {
			
			System.out.println("El número de transferencias y de hilos debe ser mayor que 0.");
		}

		try {

			// Creamos la carpeta de la ruta si no existe
			new File(ruta).mkdirs();
			
			// Objeto File del fichero de transferencias
			File ficheroTransferencias = new File(ruta + nombre);
			
			// Objeto File del fichero de bloqueo
			File ficheroBloqueo = new File(ruta + FICHERO_BLOQUEO);
			
			// Si ya existia el fichero de transferencias, lo borramos
			if (ficheroTransferencias.exists()) {
				ficheroTransferencias.delete();
			}
			// Si quedo un bloqueo de una ejecucion anterior, lo borramos tambien
			if (ficheroBloqueo.exists()) {
				ficheroBloqueo.delete();
			}


			// Lanzamos el proceso de procesar el fichero
			Process procesoProc = lanzar("../SubApProcFT/bin", "procft.SubApProcFT");
			
			// Ruta, nombre, transferencias, hilos
			/// Esta linea ---------------------------------------------------
			String[] datosProc = new String[4];
				datosProc[0] = ruta;
				datosProc[1] = nombre;
				datosProc[2] = "" + numTransferencias;
				datosProc[3] = "" + numHilos;
			
			// Lo enviamos
			enviarDatos(procesoProc, datosProc);

			// Esperamos medio segundo
			Thread.sleep(500);
			
			// Si el proceso ya no esta vivo es que ha fallado
			if (!procesoProc.isAlive() && procesoProc.exitValue() != 0)
				System.out.println("Falló el proceso");

			// Lanzamos el proceso de generar el fichero
			Process procesoGen = lanzar("../SubApGenFT/bin", "genft.SubApGenFT");
			
			// Ruta, nombre, transferencias, hilos
			/// Esta linea ---------------------------------------------------
			String[] datosGen = new String[4];
				datosProc[0] = ruta;
				datosProc[1] = nombre;
				datosProc[2] = "" + numTransferencias;
				datosProc[3] = "" + numHilos;
			
			enviarDatos(procesoGen, datosGen);
			
			// Guardamos su codigo de salida
			int codigoGen = procesoGen.waitFor();

			// Si el codigo no es 0
			if (codigoGen != 0) {
				if (codigoGen == 1) {
					System.out.println("El fichero al generar el fichero transferencias.");
				} else {
					System.out.println("Fallo al generar el fichero de transferencias.");
				}
				procesoProc.destroy();
				
				// Mostramos el valor de salida y terminamos con el codigo -2
				System.out.println("Valor de Salida: " + -2);
			}

			// Esperamos a que acabe de procesar y guardamos su codigo de salida
			int codigoProc = procesoProc.waitFor();

			// Si no es 0
			if (codigoProc != 0) {
				System.out.println("Error: falló el proceso de procesamiento del fichero de transferencias.");
				System.out.println("Valor de Salida: " + -1);
			}

		} catch (IOException e) {
			
			// Si no se pudo lanzar alguno de los procesos
			System.out.println("Error lanzando las aplicaciones: " + e.getMessage());
		} catch (InterruptedException e) {
			
			// Si nos interrumpen mientras esperabamos
			System.out.println("Error: me han interrumpido mientras esperaba.");
		}

		// Si llegamos aqui todo ha ido bien
		System.out.println("Valor de Salida: " + 0);
	}

	private static Process lanzar(String classpath, String clase) throws IOException {
		
		ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, clase);
		
		// Arrancamos el proceso
		Process proceso = constructor.start();
		
		return proceso;
	}
	
	private static void enviarDatos(Process proceso, String[] datos) throws IOException {
		
		// Para enviarle las cosas
		OutputStreamWriter osw = new OutputStreamWriter(proceso.getOutputStream(), "UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		
		// Recorremos todos los datos
		for (String dato : datos) {
			// Escribimos cada dato en una linea
			pw.println(dato);
		}
		// Cerramos
		pw.close();
	}
}
