package PracticaMultihiloI;

public class Ejercicio1 extends Thread {

    private boolean pares;

    public Ejercicio1(boolean pares) {
        this.pares = pares;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {

            // Los pares
            if (pares && i % 2 == 0) {
                System.out.println("Par: " + i);
            }

            // Los impares
            if (!pares && i % 2 != 0) {
                System.out.println("Impar: " + i);
            }
        }
    }

    public static void main(String[] args) {

        // Hilo para los pares
    	Ejercicio1 hiloPares = new Ejercicio1(true);

        // Hilo para los impares
    	Ejercicio1 hiloImpares = new Ejercicio1(false);

        // Los iniciamos
        hiloPares.start();
        hiloImpares.start();
    }
}
