package Ejercicio4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
        Path ruta = Paths.get("C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin1AAD\\Ficheros\\paises.csv");
        List<String> resultado = new ArrayList<>();

        // 1) Leer TODO el fichero antes de modificarlo
        List<String> lineas;
        try {
            lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        // 2) Procesar las líneas
        for (int i = 0; i < lineas.size(); i++) {
            String[] campos = lineas.get(i).split(",", -1);

            if (i == 0) {
                // Cabecera: se conserva, pero sin la columna de la moneda
                if (campos.length == 4) {
                    resultado.add(sinMoneda(campos));
                } else {
                    resultado.add(lineas.get(i));
                }
                continue;	//Se salta la siguiente linea
            }

            // Líneas erróneas: se ignoran
            if (campos.length != 4) {
                continue;	//Se salta la siguiente linea
            }

            resultado.add(sinMoneda(campos));
        }

        // 3) Sobrescribir el fichero original (si estaba vacío, queda vacío)
        try {
            Files.write(ruta, resultado, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    /** Devuelve la línea con País, Capital y Animal (sin el campo 2, la moneda). */
    private static String sinMoneda(String[] campos) {
        return campos[0] + "," + campos[1] + "," + campos[3];
    }

}
