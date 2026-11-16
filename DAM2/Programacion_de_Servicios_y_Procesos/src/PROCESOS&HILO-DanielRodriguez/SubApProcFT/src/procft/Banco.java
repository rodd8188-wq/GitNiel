package procft;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Banco {

	// Lista de transferencias
	private ArrayList<Transferencia> transferencias;
	
	// Posicion de la siguiente transferencia 
	private int siguiente;
	
	// Saldo de la cuenta
	private double saldo;
	
	private PrintWriter ficheroInternas;
	private PrintWriter ficheroExternas;
	private PrintWriter ficheroSinSaldo;

	public Banco(ArrayList<Transferencia> transferencias, double saldoInicial, String ruta) throws IOException {
		this.transferencias = transferencias;
		this.saldo = saldoInicial;
		this.siguiente = 0;
		this.ficheroInternas = new PrintWriter(ruta + SubApProcFT.FICHERO_INTERNAS, "UTF-8");
		this.ficheroExternas = new PrintWriter(ruta + SubApProcFT.FICHERO_EXTERNAS, "UTF-8");
		this.ficheroSinSaldo = new PrintWriter(ruta + SubApProcFT.FICHERO_SIN_SALDO, "UTF-8");
	}
	
	public synchronized Transferencia siguienteTransferencia() {
		// Devolvemos null para avisar de que no quedan
		if (siguiente >= transferencias.size()) {
			return null;
		}
		
		Transferencia tr = transferencias.get(siguiente);
		
		// La siguiente
		siguiente++;
		
		// Devolvemos la transferencia
		return tr;
	}
	
	public synchronized boolean procesar(Transferencia t) {
		
		// Comprobamos si tiene suficiente saldo
		if (saldo >= t.getImporte()) {
			
			// Mostramos el mensaje de actualizar el saldo
			System.out.println("Cuenta: " + t.getCuenta() + " - Actualizamos el saldo de la cuenta con el importe: " + t.getImporte() + " EUR");
			
			// Restamos el importe al saldo de la empresa
			saldo = saldo - t.getImporte();
			
			// La grabamos depende de cual sea
			if (t.esInterna()) {
				
				System.out.println("Grabamos transferencia interna. Cuenta " + t.getCuenta());
				ficheroInternas.println(t.getCuenta() + ";" + t.getImporte());
				
			} else {
				
				System.out.println("Grabamos transferencia externa. Cuenta " + t.getCuenta());
				ficheroExternas.println(t.getCuenta() + ";" + t.getImporte());
				
			}
			
			// Se ha pagado
			return true;
		} else {
			
			// No hay saldo
			System.out.println("Cuenta: " + t.getCuenta() + " - NO hay saldo para el importe: " + t.getImporte() + " EUR");
			
			// La grabamos en el fichero de sin saldo
			ficheroSinSaldo.println(t.getCuenta() + ";" + t.getImporte());
			
			//No se ha pagado
			return false;
		}
	}
	
	public synchronized double getSaldo() {
		return saldo;
	}
	
}
