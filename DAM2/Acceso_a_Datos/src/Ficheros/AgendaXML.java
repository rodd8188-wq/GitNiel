package Ficheros;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
			
			//Cojo el elemento i y lo guardo en le objeto contacto (Simplificado)
			Element contactoSim = (Element)contactos.item(i);
			
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			String telefono = contacto.getElementsByTagName("telefono").item(0).getTextContent();
			
			System.out.println(nombre  + " - " + telefono);
			
		}
	}
	
	public static void eliminarContacto(String fichero) throws Exception {
		
		final String nombreBuscado = "Daniel";
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
		
		NodeList contactos = doc.getElementsByTagName("contacto");
		
		for(int i=0; i<contactos.getLength(); i++) {
			
			Element contacto = (Element)contactos.item(i);
		
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			String telefono = contacto.getElementsByTagName("telefono").item(0).getTextContent();
			
			if(nombre.equalsIgnoreCase(nombreBuscado)) {
				
				//Eliminamos el contacto
				Element raiz = doc.getDocumentElement();
				raiz.removeChild(contacto);
				
				//Eliminamos el contacto (Simplificado)
				///doc.getDocumentElement().removeChild(contacto);
				
				//
				TransformerFactory transformerFactor = TransformerFactory.newInstance();
				Transformer transformer = transformerFactor.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				DOMSource source = new DOMSource(doc);						//Cambios
				StreamResult result = new StreamResult(fichero);			//Cambios
				transformer.transform(source, result);
				
			}
			
			
		}
		
	}

}
