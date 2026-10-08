package Boletin2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import Default.AgendaJSON;
import Default.ContactoJSON;

public class MainEj1 {

	public static void main(String[] args) {
		
		final String rutaAgendaXML = "Documentos/agenda.xml";
		
		try {
			
			HashMap<String, String> listaAgendaXML = leerAgenda(rutaAgendaXML);
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static HashMap<String, String> leerAgenda(String fichero) throws Exception {
		
		//Leemos el XML y lo almacenamos en el objeto doc
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
		
		
		// Creamos una lista iterable con la etiqueta contacto
		NodeList contactos = doc.getElementsByTagName("contacto");
		
		// Creamos un lista clave valor para almacenar los contactos por nombre y telefono
		HashMap<String, String> listaContactos = new HashMap<>();
		
		for(int i=0; i<contactos.getLength(); i++) {
			
			//Cojo el elemento i y lo guardo en le objeto contacto
			Element contacto = (Element)contactos.item(i);
			
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			String telefono = contacto.getElementsByTagName("telefono").item(0).getTextContent();
			
			//metemos en la lista la clave(telefono) y el valor(nombre)
			listaContactos.put(telefono, nombre);
		}
		return listaContactos;
	}	//	leerAgenda
	
	public static void parseToJSON(String ruta) {
		
		Gson gson = new Gson();
		
		String nombre = "nombre";
		int telefono = 676767676;
		
		String json = gson.toJson(new Contacto(nombre, telefono));
		
		try(Writer escritor = new FileWriter(ruta)){
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}	//	parseToJSON

}
