package PracticaMultiHiloIII;

public class Barca extends Thread{
	
	private static int numBarcas = 0;
	
	private int 
		id,
		max = 15,
		naufragos = 0;
	
	public Barca() {
		numBarcas++;
		id = numBarcas;
	}
	
	@Override
	public void run() {
		
	}
	
	
}
