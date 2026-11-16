package procft;

import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Scanner; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SubApProcFT {

	// Nombres de los ficheros
	public static final String FICHERO_INTERNAS = "transferencias_internas";
	public static final String FICHERO_EXTERNAS = "transferencias_externas";
	public static final String FICHERO_SIN_SALDO = "transferencias_sin_saldo";
	public static final String FICHERO_BLOQUEO = "transferencias.bloqueo";

	public static void main(String[] args) {

		// Scanner para leer de la entrada estandar en UTF-8 (tildes y ñ)
		Scanner teclado = new Scanner(System.in, "UTF-8");

		// Primera linea: la ruta
		String ruta = teclado.nextLine();
		// Segunda linea: el nombre del fichero
		String nombre = teclado.nextLine();
		// Tercera linea: numero de transferencias
		int numTransferencias = Integer.parseInt(teclado.nextLine());
		// Cuarta linea: numero de hilos
		int numHilos = Integer.parseInt(teclado.nextLine());

		// Objetos File para el fichero de transferencias y el de bloqueo
		File ficheroTransferencias = new File(ruta + nombre);
		File ficheroBloqueo = new File(ruta + FICHERO_BLOQUEO);

		// ESPERA: mientras NO exista el fichero O todavia exista el bloqueo, esperamos
		while (!ficheroTransferencias.exists() || ficheroBloqueo.exists()) {
			// Avisamos de que seguimos esperando
			System.out.println("SubApProcFT: esperando al fichero de transferencias...");
			try {
				// Esperamos 1 segundo antes de volver a comprobar
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Si nos interrumpen, avisamos y seguimos
				System.err.println("SubApProcFT: interrumpido mientras esperaba");
			}
		}

		// Lista donde guardaremos las transferencias leidas del fichero
		ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>();

		try {
			// Abrimos el fichero para leerlo linea a linea
			BufferedReader lector = new BufferedReader(new FileReader(ficheroTransferencias));
			// Variable donde guardamos cada linea leida
			String linea = lector.readLine();
			// Mientras la linea no sea null (null significa fin del fichero)
			while (linea != null) {
				
				// Separamos la linea
				String[] partes = linea.split(";");
				
				// Convertimos
				double importe = Double.parseDouble(partes[1]);
				
				// Creamos la transferencia y la metemos en la lista
				Transferencia trf = new Transferencia(partes[0], importe);
				transferencias.add(trf);
				
				// Siguiente linea
				linea = lector.readLine();
			}
			
			lector.close();
			
		} catch (IOException e) {
			
			System.err.println("Error leyendo el fichero: " + e.getMessage());
			// Codigo de error
			System.exit(1);
		}

		Random aleatorio = new Random();
		
		// Numero real aleatorio entre 1500 y 2700 (1500 + algo entre 0 y 1200)
		double importeAleatorio = 1500 + aleatorio.nextDouble() * 1200;
		
		// Lo redondeamos a 2 decimales
		importeAleatorio = redondear(importeAleatorio);
		
		// Saldo
		double saldoInicial = importeAleatorio * numTransferencias;

		// Mostrar el saldo
		System.out.println("El saldo inicial de la cuenta es: " + redondear(saldoInicial) + " EUR");

		Banco banco = null;
		try {
			
			// Creamos el banco
			banco = new Banco(transferencias, saldoInicial, ruta);
		} catch (IOException e) {
			
			System.err.println("Error creando los ficheros de salida: " + e.getMessage());
			// Codigo de error
			System.exit(1);
		}

		// Creamos un pool con tantos hilos como haya pedido el usuario
		ExecutorService es = Executors.newFixedThreadPool(numHilos);

		// Lista para guardar las tareas y poder consultar sus contadores despues
		ArrayList<HiloProcesador> hilos = new ArrayList<HiloProcesador>();

		for (int i = 0; i < numHilos; i++) {
			
			// Creamos los hilos
			HiloProcesador hilo = new HiloProcesador("Hilo " + i, banco);
			hilos.add(hilo);
			
			// Los ejecutamos
			es.execute(hilo);
		}
		es.shutdown();

		try {
			
			// Esperamos a los hilos
			es.awaitTermination(1, TimeUnit.HOURS);
			
		} catch (InterruptedException e) {
			
			// Si nos interrumpen mientras esperamos
			System.err.println("Interrumpido esperando");
		}

		// Variables para los totales
		int totalProcesadas = 0;
		double totalDineroProcesado = 0;
		double totalDineroPagado = 0;
		int totalInternas = 0;
		int totalExternas = 0;
		int totalSinSaldo = 0;

		// Recorremos todos los hilos sumando sus contadores
		for (HiloProcesador h : hilos) {
			
			// Transferencias procesadas
			totalProcesadas = totalProcesadas + h.getProcesadas();
			
			// Dinero procesado
			totalDineroProcesado = totalDineroProcesado + h.getDineroProcesado();
			
			// Dinero pagado
			totalDineroPagado = totalDineroPagado + h.getDineroPagado();
			
			// Internas
			totalInternas = totalInternas + h.getInternas();
			
			// Externas
			totalExternas = totalExternas + h.getExternas();
			
			// Sin saldo
			totalSinSaldo = totalSinSaldo + h.getSinSaldo();
		}

		// Resumen final
		System.out.println();
		System.out.println("===== INFORME FINAL =====");
		System.out.println("Total de transferencias procesadas: " + totalProcesadas);
		System.out.println("Total de dinero procesado: " + redondear(totalDineroProcesado) + " EUR");
		System.out.println("Total de dinero pagado: " + redondear(totalDineroPagado) + " EUR");
		System.out.println("Total de transferencias internas: " + totalInternas);
		System.out.println("Total de transferencias externas: " + totalExternas);
		System.out.println("Total de transferencias sin saldo: " + totalSinSaldo);
		System.out.println("Saldo que queda en la cuenta: " + redondear(banco.getSaldo()) + " EUR");

		// Para cada hilo
		for (HiloProcesador h : hilos) {
			System.out.println();
			System.out.println("--- " + h.getNombre() + " ---");
			System.out.println("Transferencias procesadas: " + h.getProcesadas());
			System.out.println("Dinero procesado: " + redondear(h.getDineroProcesado()) + " EUR");
			System.out.println("Dinero pagado: " + redondear(h.getDineroPagado()) + " EUR");
			System.out.println("Internas: " + h.getInternas());
			System.out.println("Externas: " + h.getExternas());
			System.out.println("Sin saldo: " + h.getSinSaldo());
		}

		System.out.println();
		System.out.println("Fin del proceso.");

		// Todo ha ido bien
		System.exit(0);
	}
	
	// Un metodo que redondea
	private static double redondear(double numero) {
		return Math.round(numero * 100) / 100.0;
	}
}
