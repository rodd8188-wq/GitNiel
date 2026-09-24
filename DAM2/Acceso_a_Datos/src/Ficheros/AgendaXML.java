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
		
		leerAgenda("Documentos/agenda.xml");
		
		modificarContacto("Daniel", "667458934", "Documentos/agenda.xml");
		
		leerAgenda("Documentos/agenda.xml");
		
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
				
				grabarXML(doc, fichero);
				
			}
		}
	}	//eliminarContacto
	
	
	public static void grabarXML(Document doc, String fichero) throws Exception {
		
		//Grabamos los cambios
		TransformerFactory transformerFactor = TransformerFactory.newInstance();
		Transformer transformer = transformerFactor.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);						//Personalizable//Creamos los elementos para añadir el nuevo contacto
		StreamResult result = new StreamResult(fichero);			//Personalizable
		transformer.transform(source, result);
		
		//Grabamos los cambios (Simplificado)
		Transformer transformerSim = TransformerFactory.newInstance().newTransformer();
		transformerSim.setOutputProperty(OutputKeys.INDENT, "yes");
		transformerSim.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformerSim.transform(new DOMSource(doc), new StreamResult(fichero));			//Personalizable
	}
	
	public static Document leerXML(String fichero) throws Exception {
		//Leemos el XML y lo devolvemos (Muy simplificado)
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
	}
	
	public static void nuevoContacto(String fichero) throws Exception {
		
		final String nombreNuevo = "Daniel";
		final String telefonoNuevo = "657347823";
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
		
		NodeList contactos = doc.getElementsByTagName("contacto");
		
		for(int i=0; i<contactos.getLength(); i++) {
			
			Element contacto = (Element)contactos.item(i);
		
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			String telefono = contacto.getElementsByTagName("telefono").item(0).getTextContent();
			
			if(nombre.equalsIgnoreCase(nombreNuevo)) {
				
				//Creamos los elementos para añadir el nuevo contacto
				Element nuevoContacto = doc.createElement("contacto");
				Element elementoNombre = doc.createElement("nombre");
				Element elementoTelefono = doc.createElement("telefono");
				elementoNombre.setTextContent(nombreNuevo);
				elementoTelefono.setTextContent(telefonoNuevo);
				nuevoContacto.appendChild(elementoNombre);
				nuevoContacto.appendChild(elementoTelefono);
				Element raiz = doc.getDocumentElement();
				raiz.appendChild(nuevoContacto);
				
				//Y al haberlos añadido los grabamos
				grabarXML(doc, fichero);
				
				//Creamos los elementos para añadir el nuevo contacto (Simplificado)
				Element nuevoContactoSim = doc.createElement("contacto");
				nuevoContacto.appendChild(doc.createElement("nombre")).setTextContent(nombreNuevo);
				nuevoContacto.appendChild(doc.createElement("telefono")).setTextContent(telefonoNuevo);
				doc.getDocumentElement().appendChild(nuevoContacto);

				
				
			}
		}
	}
	
	public static void modificarContacto(String nombreBuscado, String nuevoTelefono, String fichero) throws Exception {
		
		Document doc = leerXML(fichero);
		
		NodeList listaContactos = doc.getElementsByTagName("contacto");
		boolean encontrado = false;
		for(int i=0; i<listaContactos.getLength() && encontrado == false; i++) {
			Node nodo = listaContactos.item(i);
			Element contacto = (Element)nodo;
			String nombre = contacto.getElementsByTagName("nombre").item(0).getTextContent();
			if(nombre.equalsIgnoreCase(nombreBuscado)) {
				encontrado = true;
				Element telefono = (Element)contacto.getElementsByTagName("telefono").item(0);
				telefono.setTextContent(nuevoTelefono);
				grabarXML(doc, fichero);
				System.out.println("Telefono modificado en el contacto " + nombreBuscado);
			}
		}
		
	}

}
