package PracticaMultiHiloIII;

public class Contador extends Thread{

	private Isla isla;
	private int tiempo = 0;
	
	public Contador(Isla isla) {
		this.isla = isla;
	}
	
	@Override
	public void run() {
		
		while (isla.getNumNaufragos() > 0) {
			try {
				
				sleep(100);
				tiempo += 1;
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("Contador parado");
        System.out.println("El rescate ha concluido");
        System.out.println("\nHan pasado " + this.getTiempo() + " segundos");
	}
	
	public double getTiempo() {
		return  (double) this.tiempo / 10.0;
	}
}
