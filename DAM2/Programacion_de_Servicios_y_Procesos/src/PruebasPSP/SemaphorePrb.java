package PruebasPSP;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphorePrb {

	public static void main(String[] args) throws Exception {
		
		limitarRecurso();
		
	} // main

	public static void semaphoreMetodos() throws Exception {

		Semaphore semaforo = new Semaphore(3);

		// Solicita un permiso. Si no hay permisos, el hilo espera
		semaforo.acquire();
		try {

			/// Usamos el recurso

		} catch (Exception e) {
			// Libera un permiso
			semaforo.release();
		}

		// Devuelve los permisos disponibles
		System.out.println(semaforo.availablePermits());

		// Intenta conseguir un permiso sin esperar
		semaforo.tryAcquire();

		semaforo.tryAcquire(100, TimeUnit.SECONDS);

	}

	public static void limitarRecurso() {
		
		Semaphore semaforo = new Semaphore(2);
		
		for (int i = 1; i <= 5; i++) {
			int id = i;
			
			new Thread(() -> {
				
				try {
					System.out.println("Hilo " + id + " quiere utilizar la impresora");
					
					semaforo.acquire();
					
					try {
						System.out.println("Hilo " + id + " está utilizando la impresora");
						
						Thread.sleep(3000);
						
					} finally {
						System.out.println("Hilo " + id + " libera la impresora");
						
						semaforo.release();
					}
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				
			}).start();
		}
	} // limitarRecursos

} // class
