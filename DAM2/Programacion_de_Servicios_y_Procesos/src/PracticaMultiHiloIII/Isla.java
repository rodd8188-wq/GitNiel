package PracticaMultiHiloIII;

import java.util.concurrent.Semaphore;

public class Isla {
	
	private final int MAX_NAUFRAGOS = 100;
	private final int MIN_NAUFRAGOS = 80;
	
	private int numNaufragos = (int)(Math.random()*(MAX_NAUFRAGOS-MIN_NAUFRAGOS+1))+MIN_NAUFRAGOS;
	
	private Semaphore semaforo = new Semaphore(2);
	
	public Isla() {
		
	}
	
	public Semaphore getSemaforo() {
		return this.semaforo;
	}
	
	public int getNumNaufragos() {
		return this.numNaufragos;
	}
	
	public int rescatarNaufragos(int idBarca) {
		
		int rescatados = 0;
		
        try {
            
            if(numNaufragos >= 15) {
            	rescatados = 15;
            	numNaufragos -= 15;
            } else if(numNaufragos < 15) {
            	rescatados = numNaufragos;
            	numNaufragos = 0;
            }
            
            System.out.println("La balsa actual es Balsa " + idBarca + " y rescatamos a " + rescatados);
            System.out.println("\nQuedan " + numNaufragos + " náufragos");

            Thread.sleep(randomNum(2000, 3000));
            
            

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforo.release();
        }
        
        return rescatados;
    }
	
	public int randomNum(int min, int max) {
		return (int)(Math.random()*(max-min+1))+min;
	}
	
	public void acquireSemaforo() throws InterruptedException{
		semaforo.acquire();
	}
	
}
