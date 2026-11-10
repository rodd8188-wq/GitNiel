package PracticaMultihiloIIAEREOPUERTO;


public class Aeroplano extends Thread {

    private int id;
    private boolean esAvion;
    private Aeropuerto aeropuerto;
    
    public Aeroplano(int id, boolean esAvion, Aeropuerto aeropuerto) {
        this.id = id;
        this.esAvion = esAvion;
        this.aeropuerto = aeropuerto;
    }

    @Override
    public void run() {

        try {
        	
            aeropuerto.despegar(id, esAvion);

        } catch (InterruptedException e) {
            System.out.println("Aeroplano " + id + " ha sido interrumpido.");
        }
    }
}
