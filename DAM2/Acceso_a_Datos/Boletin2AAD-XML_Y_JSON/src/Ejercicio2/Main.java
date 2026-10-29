package Ejercicio2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	///De archivo de texto a json

	// referencia (sin espacios) + descripción (cualquier texto) + stock (número final)
    private static final Pattern PATRON =
            Pattern.compile("^(\\S+)\\s+(.+?)\\s+(\\d+)$");

    public static void main(String[] args) {
        String entrada = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\stock.txt";
        String salida = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\stock.json";

        List<Articulo> articulos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
            String linea;
            int numLinea = 0;

            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                Matcher m = PATRON.matcher(linea);
                if (m.matches()) {
                    String referencia = m.group(1);
                    String descripcion = m.group(2);
                    int stock = Integer.parseInt(m.group(3));
                    articulos.add(new Articulo(referencia, descripcion, stock));
                } else {
                    System.err.println("Línea " + numLinea + " con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
            return;
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(articulos);

        try (FileWriter fw = new FileWriter(salida)) {
            fw.write(json);
        } catch (IOException e) {
            System.err.println("Error escribiendo el JSON: " + e.getMessage());
            return;
        }

        System.out.println(json);
    }

}
