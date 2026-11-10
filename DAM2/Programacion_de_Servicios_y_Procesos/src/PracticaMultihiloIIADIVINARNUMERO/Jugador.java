package PracticaMultihiloIIADIVINARNUMERO;

public class Jugador extends Thread {

    private int idJugador;
    private Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {

        // Mientras el juego no termine
        while (!arbitro.haAcabado()) {

            try {

                arbitro.esperarTurno(idJugador);

                // Comprobamos que el juego no haya terminado
                if (arbitro.haAcabado()) {
                    break;
                }

                // Entre 1 y 10
                int numero = 1 + (int) (10 * Math.random());

                arbitro.comprobarJugada(idJugador, numero);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

