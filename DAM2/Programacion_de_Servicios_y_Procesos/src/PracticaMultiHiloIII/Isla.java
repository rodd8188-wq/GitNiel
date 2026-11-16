package PracticaMultiHiloIII;

import java.util.concurrent.Semaphore;

public class Isla {
	
	private int naufragos = (int)(Math.random()*(1000-800+1))+800;
	
	private Semaphore semaforo;
	
	public Isla(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public int getNaufragos() {
		return this.naufragos;
	}
	
	public void rescate(String nombre) throws InterruptedException{
		
		System.out.println("Entra la barca " + nombre);
		
		try {
			
			this.semaforo.acquire();
			synchronized (this) {
				
				if(this.naufragos > 15) {
					
				} else if (this.naufragos <15) {
					
				} else {
					
				}
				
			}
			
		} catch (Exception e) {
			
		}
	}
	
}
