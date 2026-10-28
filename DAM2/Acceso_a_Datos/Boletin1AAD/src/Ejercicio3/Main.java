package Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	 public static void main(String[] args) {
	        String ruta = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin1AAD\\Ficheros\\paises.csv";

	        List<String> paises = new ArrayList<>();
	        List<String> capitales = new ArrayList<>();
	        List<String> monedas = new ArrayList<>();
	        List<String> animales = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
	            String linea;
	            boolean primera = true;

	            while ((linea = br.readLine()) != null) {
	                // La primera línea es la cabecera: se ignora
	                if (primera) {
	                    primera = false;
	                    continue;
	                }

	                // -1 para conservar campos vacíos finales y detectar líneas erróneas
	                String[] campos = linea.split(",", -1);

	                // Solo se aceptan líneas con exactamente 4 datos
	                if (campos.length != 4) {
	                    continue;
	                }

	                paises.add(campos[0].trim());
	                capitales.add(campos[1].trim());
	                monedas.add(campos[2].trim());
	                animales.add(campos[3].trim());
	            }
	        } catch (IOException e) {
	            System.out.println("Error al leer el fichero: " + e.getMessage());
	            return;
	        }

	        if (paises.isEmpty()) {
	            System.out.println("No hay datos de ningún país en el fichero");
	            return;
	        }

	        System.out.println("Países en el fichero: " + paises.size());
	        System.out.println("Nombres: " + unir(paises));
	        System.out.println("Las capitales de los mismos son: " + unir(capitales));
	        System.out.println("Sus monedas oficiales son: " + unir(monedas));
	        System.out.println("Sus animales más representativos son " + unir(animales));
	    }

	    /**
	     * Une los elementos separados por comas, con " y " antes del último.
	     * 1 elemento: "A"
	     * 2 elementos: "A y B"
	     * 3+ elementos: "A, B y C"
	     */
	    private static String unir(List<String> lista) {
	        int n = lista.size();
	        if (n == 1) {
	            return lista.get(0);
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < n - 1; i++) {
	            if (i > 0) {
	                sb.append(", ");
	            }
	            sb.append(lista.get(i));
	        }
	        sb.append(" y ").append(lista.get(n - 1));
	        return sb.toString();
	    }

}
