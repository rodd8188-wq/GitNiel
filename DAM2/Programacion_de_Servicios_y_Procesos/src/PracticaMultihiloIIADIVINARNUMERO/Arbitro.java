package PracticaMultihiloIIADIVINARNUMERO;


public class Arbitro {

    private int numJugadores;
    private int turno;
    private int numeroAdivinar;
    private boolean terminado;

    public Arbitro(int numJugadores) {

        this.numJugadores = numJugadores;
        this.turno = 1;
        
        // Entre 1 y 10
        this.numeroAdivinar = 1 + (int) (10 * Math.random());
        this.terminado = false;

        System.out.println("NUMERO A ADIVINAR: " + numeroAdivinar);
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean haAcabado() {
        return terminado;
    }

    public synchronized void esperarTurno(int idJugador)
            throws InterruptedException {

        // Mientras el juego no haya terminado y no sea su turno,
        while (!terminado && turno != idJugador) {
            wait();
        }
    }

    // Método que comprueba la jugada de un jugador
    public synchronized void comprobarJugada(int idJugador, int numero) {

        // Comprobamos que el juego sigue activo y que es su turno
        if (terminado || turno != idJugador) {
            return;
        }

        System.out.println("Jugador" + idJugador + " dice: " + numero);

        // Comprobamos el número
        if (numero == numeroAdivinar) {

            terminado = true;

            System.out.println("Jugador " + idJugador + " gana, adivinó el número!!!");

        } else {

            // Siguiente jugador
            turno++;

            // Si hemos llegado al último jugador volvemos al primero
            if (turno > numJugadores) {
                turno = 1;
            }

            System.out.println(" - Le toca al Jugador " + turno);
        }

        // Despertamos a los jugadores que estaban esperando su turno
        notifyAll();
    }
}