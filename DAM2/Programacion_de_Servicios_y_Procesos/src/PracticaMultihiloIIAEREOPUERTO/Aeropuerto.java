package PracticaMultihiloIIAEREOPUERTO;


public class Aeropuerto {

    private int avionesEsperando = 0;
    private int avionetasEsperando = 0;
    private boolean pistaOcupada = false;
    private int contadorDespegues = 0;

    public void despegar(int id, boolean esAvion)
            throws InterruptedException {

        // Zona de espera
        synchronized (this) {

            if (esAvion) {
                avionesEsperando++;
            } else {
                avionetasEsperando++;
            }

            boolean esperando = true;

            try {

                // Esperamos mientras la pista esté ocupada
                while (pistaOcupada ||
                       (!esAvion && avionesEsperando > 0)) {

                    wait();
                }

                // Dejamos de contar como aeroplano en espera
                if (esAvion) {
                    avionesEsperando--;
                } else {
                    avionetasEsperando--;
                }

                esperando = false;

                // Indicamos que la pista está ocupada
                pistaOcupada = true;

                // Actualizamos el número de despegues realizados
                contadorDespegues++;

                // Que tipo es
                String tipo = esAvion ? "Avión" : "Avioneta";

                System.out.println("Despegue num " + contadorDespegues +": " + tipo + " " + id + " despegando"
                );

            } catch (InterruptedException e) {

                // Si se interrumpe mientras espera,
                if (esperando) {
                	// Lo eliminamos
                    if (esAvion) {
                        avionesEsperando--;
                    } else {
                        avionetasEsperando--;
                    }
                }

                // Avisamos a los hilos
                notifyAll();
                
                // Lanzamos una excepcion
                throw e;
            }
        }

        try {
        	
            if (esAvion) {

            	// Esperamos
                Thread.sleep(3000);

            } else {

            	// Esperamos
                Thread.sleep(2000);
            }

        } finally {

            // Liberamos la pista
            synchronized (this) {

                pistaOcupada = false;

                // Avisamos
                notifyAll();
            }
        }
    }
}
