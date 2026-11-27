package PracticaMultiHiloIII;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Isla isla = new Isla();
        
        System.out.println(isla.getNumNaufragos());
        
        Contador contador = new Contador(isla);
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
	        executor.submit(new Barca(isla, 1));
	        executor.submit(new Barca(isla, 2));
	        executor.submit(new Barca(isla, 3));
	        executor.submit(new Barca(isla, 4));
	        executor.submit(new Barca(isla, 5));
	       
	    contador.start();
        executor.shutdown();
        System.out.println();
        
    }
}
