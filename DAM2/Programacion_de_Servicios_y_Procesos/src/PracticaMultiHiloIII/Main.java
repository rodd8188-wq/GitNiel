package PracticaMultiHiloIII;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(2);
		Isla isla = new Isla();
		
		System.out.println(isla.getNaufragos());
		
		
		while(isla.getNaufragos()>0) {
			
		}
	}

}
