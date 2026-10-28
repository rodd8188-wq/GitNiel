package Ejercicio7;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
        String ruta = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin1AAD\\Ficheros\\coordenadas.dat";
        Locale es = Locale.forLanguageTag("es-ES");
        int registros = 0;

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(ruta)))) {

            while (true) {
                int id = in.readInt();
                float latitud = in.readFloat();
                float longitud = in.readFloat();

                StringBuilder estado = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    estado.append(in.readChar());
                }

                System.out.println(String.format(es,
                        "Satélite ID: %d | Posición: (%.4f, %.4f) | Estado: %s",
                        id, latitud, longitud, estado));
                registros++;
            }

        } catch (EOFException e) {
            // Fin del fichero: es la forma normal de terminar la lectura
            if (registros == 0) {
                System.out.println("ADVERTENCIA: el fichero no contiene ningún registro.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

}
