package procft;

public class Transferencia {

	private String cuenta;
	private double importe;

	public Transferencia(String cuenta, double importe) {
		this.cuenta = cuenta;
		this.importe = importe;
	}

	public String getCuenta() {
		return cuenta;
	}

	public double getImporte() {
		return importe;
	}
	
	// Comprueba si es interna o no
	// true si la transferencia es interna
	public boolean esInterna() {
		
		if(cuenta.charAt(0) == '1')
			return true;
		else
			return false;
	}
}
