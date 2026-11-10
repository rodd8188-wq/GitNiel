package PracticaMultihiloI;

import java.util.Random;

public class Ejercicio5 {

    private static boolean desactivada = false;


    public static void cuentaAtras(Thread artificiero) {

        try {

            // Contamos desde 9 hasta 0
            for (int i = 9; i >= 0; i--) {

            	// Si la bomba termina
                if (desactivada) {

                    System.out.println("La bomba ha sido desactivada");
                    return;
                }

                System.out.println(i);

                // Un segundo
                Thread.sleep(1000);
            }

            // No la desactivó
            if (!desactivada) {

                System.out.println("BOOOOOOM");

                // Le cortamos
                artificiero.interrupt();
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    
    public static void artificiero() {

        try {

            System.out.println("El artificiero comienza a desactivar la bomba");

            Random random = new Random();


            // Entre 9500 y 10500 milisegundos.
            int tiempo = 9500 + random.nextInt(1001);

            // Esperamos mientras intenta desactivar la bomba
            Thread.sleep(tiempo);
            
            // Si no le han cortado la ha desactivado
            desactivada = true;

            System.out.println("El artificiero ha desactivado la bomba");

        } catch (InterruptedException e) {
        	
        	// Si le cortan
            System.out.println("El artificiero ha muerto");
        }
    }


    public static void main(String[] args) {


        Thread hiloArtificiero = new Thread(() -> {
        	artificiero();
        });

        Thread hiloBomba = new Thread(() -> {
            cuentaAtras(hiloArtificiero);
        });

        // Iniciamos hilos
        hiloArtificiero.start();
        hiloBomba.start();
    }
}
