package PracticaMultihiloI;

public class Ejercicio4Carrera {

    public static void main(String[] args) {
    	
    	// Corredores de España 
        Ejercicio4 pepe = new Ejercicio4("Pepe", "España", null);

        Ejercicio4 maria = new Ejercicio4("Maria", "España", pepe);

        Ejercicio4 juan = new Ejercicio4("Juan", "España", maria);

        Ejercicio4 marta = new Ejercicio4("Marta", "España", juan);


        // Corredores de EEUU.
        Ejercicio4 john = new Ejercicio4("John", "EEUU", null);

        Ejercicio4 kim = new Ejercicio4("Kim", "EEUU", john);

        Ejercicio4 mike = new Ejercicio4("Mike", "EEUU", kim);

        Ejercicio4 sara = new Ejercicio4("Sarah", "EEUU", mike);



        pepe.start();
        maria.start();
        juan.start();
        marta.start();

        john.start();
        kim.start();
        mike.start();
        sara.start();

        //España
        try {

            marta.join();

            System.out.println("Termina España");

            //EEUU

            sara.join();

            System.out.println("Termina EEUU");

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}
