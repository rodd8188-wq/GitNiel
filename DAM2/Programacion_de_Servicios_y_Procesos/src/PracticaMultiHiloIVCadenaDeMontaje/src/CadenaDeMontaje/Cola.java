package CadenaDeMontaje;

public class Cola {
	
	private int[] cadena = new int[3];
	private boolean disponible = true;
	
	public synchronized void colocar() {
		
		while(disponible == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			
			}
		}
		
			
	}

}
