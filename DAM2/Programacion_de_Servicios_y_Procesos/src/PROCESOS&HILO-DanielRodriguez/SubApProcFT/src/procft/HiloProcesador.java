package procft;

public class HiloProcesador implements Runnable {

	private String nombre;
	private Banco banco;
	private int procesadas = 0;
	private double dineroProcesado = 0;
	private double dineroPagado = 0;
	private int internas = 0;
	private int externas = 0;
	private int sinSaldo = 0;
	
	public HiloProcesador(String nombre, Banco banco) {
		this.nombre = nombre;
		this.banco = banco;
	}

	@Override
	public void run() {
		
		// Si ponemos esto se repite hasta un break
		while (true) {
			
			// Pedimos una transferencia
			Transferencia tr = banco.siguienteTransferencia();

			// Si es null ya no quedan transferencias
			if (tr == null) {
				break;
			}

			// Si es interna
			if (tr.esInterna()) {
				
				try {
					
					// 1 segundo
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					
					// Esto pasa si lo cortan mientras hace lo suyo
					System.err.println(nombre + " interrumpido");
					
				}
			}

			// Lo procesamos
			boolean pagada = banco.procesar(tr);

			procesadas++;
			
			// Sumamos el dinero procesado
			dineroProcesado = dineroProcesado + tr.getImporte();

			// Si no tiene suficiente
			if (pagada) {
				
				// Sumamos el importe al dinero pagado
				dineroPagado = dineroPagado + tr.getImporte();
				
				// Si es interna o externa
				if (tr.esInterna()) {
					internas++;
				} else {
					externas++;
				}
			} else {
				
				// Si no se pudo pagar
				sinSaldo++;
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public int getProcesadas() {
		return procesadas;
	}

	public double getDineroProcesado() {
		return dineroProcesado;
	}

	public double getDineroPagado() {
		return dineroPagado;
	}

	public int getInternas() {
		return internas;
	}

	public int getExternas() {
		return externas;
	}

	public int getSinSaldo() {
		return sinSaldo;
	}
}
