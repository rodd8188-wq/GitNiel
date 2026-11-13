package PracticaMultiHiloIII;

import java.util.concurrent.Semaphore;

public class Isla {
	
	private int naufragos;
	
	public Isla() {
		naufragos = (int)(Math.random()*(1000-800+1))+800;
	}
	
	public int getNaufragos() {
		return this.naufragos;
	}
	
	public static void rescate(Semaphore semaforo, Barca barca) {
		
	}
	
}
