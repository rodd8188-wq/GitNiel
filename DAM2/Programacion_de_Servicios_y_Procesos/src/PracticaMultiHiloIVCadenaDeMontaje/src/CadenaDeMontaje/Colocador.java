package CadenaDeMontaje;

public class Colocador extends Thread{
	
	private static int num = 0;
	private int id;
	private Cola cola;
	
	public Colocador(Cola cola) {
		this.id = (num++);
		this.cola = cola;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 20; i++) {
			
			cola.colocar();
			
			try {
				
				sleep((int)(Math.random() * (250 - 0 + 1)) + 0);
				
			} catch (InterruptedException e) {
				
			}
			
		}
		
		
			
		
	}

}
