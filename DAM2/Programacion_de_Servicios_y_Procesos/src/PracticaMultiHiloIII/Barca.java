package PracticaMultiHiloIII;

public class Barca extends Thread {
	
	private final int NUM_PLAZAS = 15;

    private Isla isla;
    private int id;
    private int totalRescatados = 0;

    public Barca(Isla isla, int id) {
        this.isla = isla;
        this.id = id;
        if(this.id == 1)
        	this.setPriority(MAX_PRIORITY);
    }
    
    @Override
    public void run() {
    	
    	System.out.println("La balsa actual es Balsa " + this.id + " y tiene " + NUM_PLAZAS + " plaza");
    	
    	try {
			
    		isla.acquireSemaforo();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        while(isla.getNumNaufragos() > 0) {
        	
        	this.totalRescatados += isla.rescatarNaufragos(id);
        	
            try {
    			sleep((int)(Math.random()*(2000-1800+1))+1800);
    		} catch (InterruptedException e) {
    			// TODO: handle exception
    		}
        	
        }
        
        System.out.println("Ya no quedan náufragos y la balsa " + this.id + " está volviendo a su puesto."
        		+ " Ha rescatado " + totalRescatados + " náufragos en total.");
    }
}
