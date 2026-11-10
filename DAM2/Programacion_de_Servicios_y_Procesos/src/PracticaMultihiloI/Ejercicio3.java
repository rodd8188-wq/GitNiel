package PracticaMultihiloI;

import java.util.Random;

public class Ejercicio3 extends Thread {

    private String nombre;
    private String equipo;
    private Thread corredorAnterior;
    
    public Ejercicio3(String nombre, String equipo, Thread corredorAnterior) {

        this.nombre = nombre;
        this.equipo = equipo;
        this.corredorAnterior = corredorAnterior;
    }

    @Override
    public void run() {

        try {
        	
        	// Esperamos al corredor anterior
            if (corredorAnterior != null) {

                corredorAnterior.join();
            }

            System.out.println(equipo + ": " + nombre + " comienza su relevo");

            Random random = new Random();

            long tiempoTotal = 0;

            for (int i = 0; i < 10; i++) {

                // 950 y 1050 ms
                int tiempo = 950 + random.nextInt(101);

                tiempoTotal += tiempo;

                // Esperamos ese tiempo
                Thread.sleep(tiempo);
            }

            System.out.println(
            		equipo + ": " + nombre + " ha acabado su relevo - Ha tardado: " + (tiempoTotal / 1000.0) + " segundos");

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        // Los corredores
    	Ejercicio3 corredor1 = new Ejercicio3("Pepe", "España", null);

    	Ejercicio3 corredor2 = new Ejercicio3("Maria", "España", corredor1);

    	Ejercicio3 corredor3 = new Ejercicio3("Juan", "España", corredor2);

    	Ejercicio3 corredor4 = new Ejercicio3("Marta", "España", corredor3);

        // Iniciamos los hilos
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
    }
}
