package PracticaMultihiloIIADIVINARNUMERO;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el número de jugadores: ");
        int numJugadores = teclado.nextInt();

        Arbitro arbitro = new Arbitro(numJugadores);
        
        Jugador[] jugadores = new Jugador[numJugadores];

        for (int i = 0; i < numJugadores; i++) {

            jugadores[i] = new Jugador(i + 1, arbitro);
        }

        // Iniciamos los hilos
        for (int i = 0; i < numJugadores; i++) {

            jugadores[i].start();
        }

        // Esperamos a que terminen
        for (int i = 0; i < numJugadores; i++) {

            try {

                jugadores[i].join();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
                
                // Para salir del bucle
                break;
            }
        }

        teclado.close();
    }
}
