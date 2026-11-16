package PracticaMultiHiloIII;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main{

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(2);
		Isla isla = new Isla(semaforo);
		
		System.out.println(isla.getNaufragos());
		
		Barca barca1 = new Barca();
		Barca barca2 = new Barca();
		Barca barca3 = new Barca();
		Barca barca4 = new Barca();
		Barca barca5 = new Barca();
		
		ExecutorService miExecutor = Executors.newFixedThreadPool(5);
			miExecutor.submit(barca1);
			miExecutor.submit(barca2);
			miExecutor.submit(barca3);
			miExecutor.submit(barca4);
			miExecutor.submit(barca5);
		miExecutor.shutdown();
		
	}

}
