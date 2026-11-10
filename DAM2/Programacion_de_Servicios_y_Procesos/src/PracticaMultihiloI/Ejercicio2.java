package PracticaMultihiloI;

public class Ejercicio2 implements Runnable {

    private String nombre;

    private double velocidadCorriendo;
    private double velocidadPedaleando;
    private double velocidadNadando;

    public Ejercicio2(String nombre, double velocidadCorriendo, double velocidadPedaleando, double velocidadNadando) {

        this.nombre = nombre;
        this.velocidadCorriendo = velocidadCorriendo;
        this.velocidadPedaleando = velocidadPedaleando;
        this.velocidadNadando = velocidadNadando;
    }

    public void bici() {

        System.out.println(nombre + " comienza la prueba en bici");

        // Calculamos las horas necesarias
        double horas = 180 / velocidadPedaleando;

        // 1 hora son 1000 ms
        long tiempo = (long) (horas * 1000);

        try {

            Thread.sleep(tiempo);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(nombre + " ha acabado la prueba en bici");
    }

    public void marathon() {

        System.out.println(nombre + " comienza la marathon");

        // Calculamos las horas necesarias
        double horas = 42 / velocidadCorriendo;

        // Convertimos las horas a milisegundos
        long tiempo = (long) (horas * 1000);

        try {

            Thread.sleep(tiempo);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(nombre + " ha acabado la marathon");
    }

    public void nadar() {

        System.out.println(nombre + " comienza la prueba de natación");

        /*
         * La velocidad está expresada en metros por hora.
         */
        double horas = 4000 / velocidadNadando;

        // Convertimos las horas a milisegundos
        long tiempo = (long) (horas * 1000);

        try {

            Thread.sleep(tiempo);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(nombre + " ha acabado la prueba de natación");
    }

    @Override
    public void run() {

        bici();
        marathon();
        nadar();

        System.out.println(nombre + " ha terminado el IronMan");
    }

    public static void main(String[] args) {

        // Los participantes
    	Ejercicio2 pedro = new Ejercicio2("Pedro", 6, 20, 1000);

        Ejercicio2 juan = new Ejercicio2("Juan", 7, 18, 2000);

        Ejercicio2 vicente = new Ejercicio2( "Vicente", 6, 30, 1000);

        // Hilos para cada participante
        Thread hiloPedro = new Thread(pedro);
        Thread hiloJuan = new Thread(juan);
        Thread hiloVicente = new Thread(vicente);
        
        
        // Todos empiezan a la vez
        hiloPedro.start();
        hiloJuan.start();
        hiloVicente.start();
    }
}
