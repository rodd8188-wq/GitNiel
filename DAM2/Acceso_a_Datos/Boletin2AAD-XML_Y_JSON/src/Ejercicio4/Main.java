package Ejercicio4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
	
	 private static final String ARCHIVO_XML = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\pokemons.txt";
	 private static final String ARCHIVO_JSON = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\pokemons.json";

	public static void main(String[] args) {
        String entrada = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\pokemons.txt";   // archivo de origen
        String salida = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin2AAD-XML_Y_JSON\\Ficheros\\pokemons.json";   // archivo JSON que vamos a generar

        // Aquí iremos acumulando todos los Pokémon leídos
        List<Pokemon> pokemons = new ArrayList<>();

        // 1. Abrimos el archivo (try-with-resources lo cierra solo al terminar)
        try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
            String linea;
            int numLinea = 0;

            // 2. Leemos línea a línea hasta el fin del archivo (readLine() devuelve null)
            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;   // saltamos líneas en blanco

                // 3. Separamos por comas quitando los espacios alrededor
                //    "167, Spinarak, 8.5, 0.5, Bicho, Veneno"
                //    -> ["167", "Spinarak", "8.5", "0.5", "Bicho", "Veneno"]
                String[] campos = linea.split("\\s*,\\s*");

                // Mínimo 5 campos: código, nombre, peso, altura y un tipo
                if (campos.length < 5) {
                    System.err.println("Línea " + numLinea + " con formato incorrecto: " + linea);
                    continue;
                }

                try {
                    // 4. A diferencia del XML (donde todo era texto), en JSON los números
                    //    deben ser números, así que convertimos los campos numéricos.
                    int codigo = Integer.parseInt(campos[0]);
                    String nombre = campos[1];
                    double peso = Double.parseDouble(campos[2]);
                    double altura = Double.parseDouble(campos[3]);

                    // 5. Unimos todos los tipos restantes en una sola cadena: "Bicho, Veneno"
                    String tipo = String.join(", ",
                            Arrays.copyOfRange(campos, 4, campos.length));

                    pokemons.add(new Pokemon(codigo, nombre, peso, altura, tipo));

                } catch (NumberFormatException e) {
                    // Si el código, peso o altura no son números válidos
                    System.err.println("Línea " + numLinea + " con datos numéricos incorrectos: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
            return;
        }

        // 6. Convertimos la lista completa a JSON. setPrettyPrinting() añade
        //    saltos de línea y sangría para que sea legible.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(pokemons);

        // 7. Escribimos el JSON en el archivo de salida
        try (FileWriter fw = new FileWriter(salida)) {
            fw.write(json);
        } catch (IOException e) {
            System.err.println("Error escribiendo el JSON: " + e.getMessage());
            return;
        }

        System.out.println("Archivo " + salida + " generado correctamente.");
    }
	
	public enum Formato {
	    XML,
	    JSON
	}
	
	 public static boolean altaPokemon(Pokemon p, Formato formato) {
	        try {
	            switch (formato) {
	                case XML:
	                    return guardarEnXml(p);
	                case JSON:
	                    return guardarEnJson(p);
	                default:
	                    return false;
	            }
	        } catch (Exception e) {
	            System.err.println("Error al dar de alta a " + p.getNombre() + ": " + e.getMessage());
	            return false;
	        }
	    }

	    // ==================================================================
	    //                              JSON
	    // ==================================================================
	    private static boolean guardarEnJson(Pokemon nuevo) throws IOException {
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        List<Pokemon> pokemons = new ArrayList<>();

	        // 1. Si el archivo ya existe, cargamos los Pokémon que contiene
	        File f = new File(ARCHIVO_JSON);
	        if (f.exists()) {
	            try (FileReader r = new FileReader(f)) {
	                List<Pokemon> leidos = gson.fromJson(r,
	                        new TypeToken<List<Pokemon>>() {}.getType());
	                if (leidos != null) pokemons = leidos;   // null si el archivo está vacío
	            }
	        }

	        // 2. Evitamos duplicados por código de Pokédex
	        for (Pokemon existente : pokemons) {
	            if (existente.getCodigo() == nuevo.getCodigo()) {
	                System.err.println("Ya existe un Pokémon con el código " + nuevo.getCodigo());
	                return false;
	            }
	        }

	        // 3. Añadimos el nuevo y reescribimos el archivo completo
	        pokemons.add(nuevo);
	        try (FileWriter w = new FileWriter(f)) {
	            gson.toJson(pokemons, w);
	        }
	        return true;
	    }

	    // ==================================================================
	    //                              XML
	    // ==================================================================
	    private static boolean guardarEnXml(Pokemon nuevo) throws Exception {
	        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        File f = new File(ARCHIVO_XML);
	        Document doc;
	        Element raiz;

	        // 1. Si el archivo existe lo cargamos; si no, creamos uno nuevo con raíz <pokemons>
	        if (f.exists() && f.length() > 0) {
	            doc = db.parse(f);
	            raiz = doc.getDocumentElement();
	            quitarEspacios(raiz);   // evita líneas en blanco extra al reescribir
	        } else {
	            doc = db.newDocument();
	            raiz = doc.createElement("pokemons");
	            doc.appendChild(raiz);
	        }

	        // 2. Evitamos duplicados recorriendo las etiquetas <codigo> existentes
	        NodeList codigos = doc.getElementsByTagName("codigo");
	        for (int i = 0; i < codigos.getLength(); i++) {
	            if (codigos.item(i).getTextContent().trim()
	                    .equals(String.valueOf(nuevo.getCodigo()))) {
	                System.err.println("Ya existe un Pokémon con el código " + nuevo.getCodigo());
	                return false;
	            }
	        }

	        // 3. Construimos el <pokemon> nuevo y lo colgamos de la raíz
	        Element pokemon = doc.createElement("pokemon");
	        agregar(doc, pokemon, "codigo", String.valueOf(nuevo.getCodigo()));
	        agregar(doc, pokemon, "nombre", nuevo.getNombre());
	        agregar(doc, pokemon, "peso", String.valueOf(nuevo.getPeso()));
	        agregar(doc, pokemon, "altura", String.valueOf(nuevo.getAltura()));
	        agregar(doc, pokemon, "tipo", nuevo.getTipo());
	        raiz.appendChild(pokemon);

	        // 4. Volcamos el documento completo al archivo
	        Transformer t = TransformerFactory.newInstance().newTransformer();
	        t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        t.transform(new DOMSource(doc), new StreamResult(f));
	        return true;
	    }
	    
	    // Crea <nombre>valor</nombre> y lo añade al padre (igual que en el ejercicio 3)
	    private static void agregar(Document doc, Element padre, String nombre, String valor) {
	        Element e = doc.createElement(nombre);
	        e.setTextContent(valor);
	        padre.appendChild(e);
	    }
	    
	    // Elimina los nodos de texto que solo contienen espacios o saltos de línea.
	    // Al leer un XML con sangría, esos nodos se cargan y al reescribir
	    // aparecerían como líneas en blanco.
	    private static void quitarEspacios(Node nodo) {
	        NodeList hijos = nodo.getChildNodes();
	        for (int i = hijos.getLength() - 1; i >= 0; i--) {
	            Node hijo = hijos.item(i);
	            if (hijo.getNodeType() == Node.TEXT_NODE
	                    && hijo.getTextContent().trim().isEmpty()) {
	                nodo.removeChild(hijo);
	            } else {
	                quitarEspacios(hijo);
	            }
	        }
	    }

}
