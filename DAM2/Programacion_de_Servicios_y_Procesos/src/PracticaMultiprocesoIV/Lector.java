package PracticaMultiprocesoIV;

import java.io.BufferedReader;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Lector {

    public static void main(String[] args) {

        // Creamos las rutas
        Path ficheroDatos = Path.of("datos.txt");
        Path ficheroBloqueo = Path.of("datos.lock");

        System.out.println("Esperando que se libere el bloqueo...");

        //Abrimos el fichero de bloqueo
        try (FileChannel canalBloqueo = FileChannel.open(
                ficheroBloqueo,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)) {

        	//Si Escritor tiene el bloqueo tocará esperar
            try (FileLock bloqueo = canalBloqueo.lock()) {

                // Abrimos datos.txt
                try (BufferedReader lector = Files.newBufferedReader(
                        ficheroDatos)) {

                    String linea;

                    // Leemos el fichero
                    while ((linea = lector.readLine()) != null) {

                        System.out.println("Leído mensaje: " + linea);
                    }
                }
            }

            System.out.println("Fin de lectura del fichero.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
