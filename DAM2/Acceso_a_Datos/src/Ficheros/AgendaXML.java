package Ficheros;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class AgendaXML {

	public static void main(String[] args) throws Exception {
		
		leerAgenda("agenda.xml");
		
	}
	
	public static void leerAgenda(String fichero) throws Exception {
		
		//Leemos el XML y lo almacenamos en el objeto doc
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fichero);
		
		//Leemos el XML y lo almacenamos en el objeto doc (Simplificado)
		DocumentBuilder builderSim = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document docSim = builderSim.parse(fichero);
		
		//Leemos el XML y lo almacenamos en el objeto doc (Muy simplificado)
		Document docMSim = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
		
		
		// Creamos una lista iterable con la etiqueta contacto
		NodeList contactos = doc.getElementsByTagName("contacto");
		
		for(int i=0; i<contactos.getLength(); i++) {
			
			//Cojo el elemento i y lo guardo en le objeto contacto
			Node nodo = contactos.item(i);
			Element contacto = (Element)nodo;
			
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			String telefono = contacto.getElementsByTagName("telefono").item(0).getTextContent();
			
			System.out.println(nombre  + " - " + telefono);
			
		}
	}

}
