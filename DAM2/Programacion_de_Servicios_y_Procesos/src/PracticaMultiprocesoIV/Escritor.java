package PracticaMultiprocesoIV;

import java.io.BufferedWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Escritor {

    public static void main(String[] args) {

        // Creamos las rutas de los dos ficheros que vamos a utilizar
        Path ficheroDatos = Path.of("datos.txt");
        Path ficheroBloqueo = Path.of("datos.lock");

        // Objeto para generar tiempos de espera aleatorios
        Random random = new Random();

        //Abrimos el fichero de bloqueo
        try (FileChannel canalBloqueo = FileChannel.open(ficheroBloqueo, 
        		StandardOpenOption.CREATE, StandardOpenOption.WRITE);

             //Abrimos el fichero de datos.
             BufferedWriter escritor = Files.newBufferedWriter(ficheroDatos,
                     StandardOpenOption.CREATE,
                     StandardOpenOption.WRITE,
                     StandardOpenOption.TRUNCATE_EXISTING)) {

            // Hacemos el bloqueo sobre datos
            try (FileLock bloqueo = canalBloqueo.lock()) {

                System.out.println(
                        "Establecido bloqueo para escribir los mensajes..."
                );

                // Escribimos 10 mensajes en el fichero
                for (int i = 0; i < 10; i++) {

                    String mensaje = "Mensaje de iteración :" + i;

                    escritor.write(mensaje);

                    escritor.newLine();

                    escritor.flush();

                    System.out.println("Escritor: Escrito mensaje de iteración " + i);

                    // Entre 1000 y 1500 milisegundos
                    int tiempoEspera = 1000 + random.nextInt(501);

                    Thread.sleep(tiempoEspera);
                }

            }

            System.out.println("Liberado bloqueo.");

        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
            
        }

    }
}