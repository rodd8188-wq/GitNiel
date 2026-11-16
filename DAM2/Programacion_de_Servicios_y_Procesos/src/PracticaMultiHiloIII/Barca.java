package PracticaMultiHiloIII;

public class Barca extends Thread{
	
	private static int numBarcas = 0;
	
	private int 
		id,
		naufragosTotal = 0;
	
	public Barca() {
		numBarcas++;
		id = numBarcas;
	}
	
	@Override
	public void run() {
		System.out.println("La barca actual es la " + this.id);
		isla.rescate();
	}
	
	
}
