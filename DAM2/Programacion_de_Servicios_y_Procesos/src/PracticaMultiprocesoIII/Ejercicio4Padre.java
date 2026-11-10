package PracticaMultiprocesoIII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio4Padre {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        while (true) {

            System.out.println("Escribe la asignatura:");
            String asignatura = teclado.nextLine();

            // Si se introduce *
            if (asignatura.equals("*")) {
                break;
            }

            System.out.println("Escribe el nombre del fichero:");
            String fichero = teclado.nextLine();

            try {

                // Ejecutamos el primer programa
                ProcessBuilder pb = new ProcessBuilder("java", "Ejercicio4_1", asignatura, fichero
                );

                Process proceso = pb.start();

                // Leemos y mostramos la salida estándar
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream())
                );

                String linea;

                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }

                // Esperamos a que termine el proceso
                int valorSalida = proceso.waitFor();

                System.out.println("Valor de salida: " + valorSalida);

            } catch (IOException e) {

                System.err.println("Error al ejecutar el proceso.");

            } catch (InterruptedException e) {

                System.err.println("El proceso fue interrumpido.");
            }
        }

        teclado.close();

        System.out.println("Programa terminado.");
    }
}
