package PracticaMultihiloIIAEREOPUERTO;


public class Main {

    public static void main(String[] args) {

        Aeropuerto aeropuerto = new Aeropuerto();

        Aeroplano[] aeroplanos = new Aeroplano[20];

        for (int i = 0; i < 20; i++) {

            // Aleatoriamente su tipo
            boolean esAvion = Math.random() < 0.5;

            // Identificador y tipo
            aeroplanos[i] = new Aeroplano(i + 1, esAvion, aeropuerto);

            String tipo;

            if (esAvion) {
                tipo = "Avión";
            } else {
                tipo = "Avioneta";
            }

            System.out.println(tipo + " " + (i + 1) + " ha llegado al aeropuerto.");

            // Lo iniciamos
            aeroplanos[i].start();

            // Para el siguiente
            if (i < 19) {

                try {

                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    System.out.println("El hilo ha sido interrumpido.");
                }
            }
        }

        
        for (int i = 0; i < 20; i++) {

            if (aeroplanos[i] != null) {

                try {

                    aeroplanos[i].join();

                } catch (InterruptedException e) {
                    System.out.println("El hilo ha sido interrumpido.");
                }
            }
        }

        System.out.println("TODOS HAN TERMINADO olee");
    }
}
