package PracticaMultiprocesoIII;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4Hijo {

    public static void main(String[] args) {

        // Comprobamos que se han recibido los 2 argumentos
        if (args.length != 2) {
            System.exit(1);
        }

        String asignatura = args[0];
        String nombreFichero = args[1];

        double suma = 0;
        int aprobados = 0;
        int totalNotas = 0;

        try (BufferedReader br = new BufferedReader(
                new FileReader(nombreFichero))) {

            String linea;

            while ((linea = br.readLine()) != null) {
            	
                double nota = Double.parseDouble(linea);

                suma += nota;
                totalNotas++;

                if (nota >= 5) {
                    aprobados++;
                }
            }

            if (totalNotas == 0) {
                System.err.println("El fichero no contiene notas.");
                System.exit(-1);
            }

            double media = suma / totalNotas;

            System.out.println("En la asignatura " + asignatura);
            System.out.println("Han aprobado " + aprobados + " alumnos");
            System.out.println("La media es " + media);

        } catch (IOException e) {

            System.err.println("Error al leer el fichero: " + e.getMessage());
            System.exit(-1);

        } catch (NumberFormatException e) {

            System.err.println("Error: hay una nota no válida en el fichero.");
            System.exit(-1);
        }
    }
}
