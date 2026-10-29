package Ejercicio3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Arrays;

public class Main {
	
	///De archivo de texto a XML
	
	public static void main(String[] args) {
        String entrada = "C:\\\\Users\\\\rumbl\\\\GitNiel\\\\GitNiel\\\\DAM2\\\\Acceso_a_Datos\\\\Boletin2AAD-XML_Y_JSON\\\\Ficheros\\\\pokemons.txt";   // archivo de origen
        String salida = "C:\\\\Users\\\\rumbl\\\\GitNiel\\\\GitNiel\\\\DAM2\\\\Acceso_a_Datos\\\\Boletin2AAD-XML_Y_JSON\\\\Ficheros\\\\pokemons.xml";    // archivo XML que vamos a generar

        try {
            // 1. Creamos un documento XML vacío en memoria
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();

            // 2. Creamos el elemento raíz <pokemons> y lo colgamos del documento.
            //    Todo XML necesita un único elemento raíz.
            Element raiz = doc.createElement("pokemons");
            doc.appendChild(raiz);

            // 3. Abrimos el archivo de texto. El try-with-resources lo cierra
            //    automáticamente al terminar, aunque haya errores.
            try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
                String linea;
                int numLinea = 0;   // para indicar en qué línea hay un error

                // Leemos línea a línea hasta que readLine() devuelva null (fin del archivo)
                while ((linea = br.readLine()) != null) {
                    numLinea++;
                    linea = linea.trim();          // quitamos espacios al principio y al final
                    if (linea.isEmpty()) continue; // saltamos las líneas en blanco

                    // 4. Separamos la línea por comas. La expresión "\\s*,\\s*"
                    //    significa "una coma con cualquier número de espacios a ambos lados",
                    //    así los campos salen ya limpios, sin necesidad de trim() en cada uno.
                    //    Ejemplo: "167, Spinarak, 8.5, 0.5, Bicho, Veneno"
                    //    -> ["167", "Spinarak", "8.5", "0.5", "Bicho", "Veneno"]
                    String[] campos = linea.split("\\s*,\\s*");

                    // Un Pokémon válido tiene como mínimo 5 campos:
                    // código, nombre, peso, altura y al menos un tipo
                    if (campos.length < 5) {
                        System.err.println("Línea " + numLinea + " con formato incorrecto: " + linea);
                        continue; // ignoramos la línea y seguimos con la siguiente
                    }

                    // 5. Creamos el elemento <pokemon> para esta línea
                    Element pokemon = doc.createElement("pokemon");

                    // Los cuatro primeros campos son fijos y siempre van en este orden
                    agregar(doc, pokemon, "codigo", campos[0]);
                    agregar(doc, pokemon, "nombre", campos[1]);
                    agregar(doc, pokemon, "peso", campos[2]);
                    agregar(doc, pokemon, "altura", campos[3]);

                    // 6. Todo lo que queda desde la posición 4 son los tipos (uno o dos).
                    //    copyOfRange(array, 4, length) copia desde el índice 4 hasta el final.
                    //    String.join los une con ", " para dejarlos en la misma línea:
                    //    ["Bicho", "Veneno"] -> "Bicho, Veneno"
                    String tipos = String.join(", ",
                            Arrays.copyOfRange(campos, 4, campos.length));
                    agregar(doc, pokemon, "tipo", tipos);

                    // 7. Añadimos el <pokemon> ya completo al elemento raíz
                    raiz.appendChild(pokemon);
                }
            }

            // 8. Escribimos el documento en disco. El Transformer convierte
            //    el árbol DOM de memoria en texto XML.
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");      // sangría para que sea legible
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  // codificación (tildes, ñ...)
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // 4 espacios por nivel

            // DOMSource = de dónde sale el XML (nuestro documento)
            // StreamResult = a dónde va (el archivo de salida)
            t.transform(new DOMSource(doc), new StreamResult(new File(salida)));

            System.out.println("Archivo " + salida + " generado correctamente.");

        } catch (Exception e) {
            // Captura cualquier error: archivo no encontrado, XML mal formado, etc.
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar que evita repetir código.
     * Crea un elemento <nombre>valor</nombre> y lo añade dentro de "padre".
     *
     * @param doc    documento XML al que pertenecen los elementos
     * @param padre  elemento dentro del cual se insertará el nuevo
     * @param nombre nombre de la etiqueta (por ejemplo "codigo")
     * @param valor  texto que irá dentro de la etiqueta
     */
    private static void agregar(Document doc, Element padre, String nombre, String valor) {
        Element e = doc.createElement(nombre); // crea la etiqueta vacía: <nombre/>
        e.setTextContent(valor);               // le pone el texto dentro: <nombre>valor</nombre>
        padre.appendChild(e);                  // la cuelga del elemento padre
    }

}
