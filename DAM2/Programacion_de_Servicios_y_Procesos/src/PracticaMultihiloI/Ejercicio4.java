package PracticaMultihiloI;

import java.util.Random;

class Ejercicio4 extends Thread {

    private String nombre;
    private String equipo;
    private Thread anterior;

    public Ejercicio4(String nombre, String equipo,
            Thread anterior) {

        this.nombre = nombre;
        this.equipo = equipo;
        this.anterior = anterior;
    }

    @Override
    public void run() {

        try {

            //Esperamos al anterior
            if (anterior != null) {

                anterior.join();
            }

            System.out.println(
                    equipo + ": " + nombre
                    + " comienza su relevo"
            );

            Random random = new Random();

            long tiempoTotal = 0;

            // Simulamos los 10 tramos de 10 metros
            for (int i = 0; i < 10; i++) {

                int tiempo = 950 + random.nextInt(101);

                tiempoTotal += tiempo;

                Thread.sleep(tiempo);
            }

            System.out.println(
                    equipo + ": " + nombre
                    + " ha acabado su relevo - Ha tardado: "
                    + (tiempoTotal / 1000.0)
                    + " segundos"
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}

