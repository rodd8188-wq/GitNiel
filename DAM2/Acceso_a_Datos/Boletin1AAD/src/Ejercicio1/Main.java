package Ejercicio1;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	final static String RUTAPERSONAJES = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin1AAD\\Ficheros\\personajes.txt";
	final static String RUTAANIMES = "C:\\Users\\rumbl\\GitNiel\\GitNiel\\DAM2\\Acceso_a_Datos\\Boletin1AAD\\Ficheros\\animes.txt";
	
	public static void main(String[] args) {
		
		
		agruparFicheros();
		
		
	}
	
	public static void agruparFicheros() {
		
		HashMap<String, Integer> listaPersonajes = leerFichero(RUTAPERSONAJES);
		HashMap<String, Integer> listaAnimes = leerFichero(RUTAANIMES);
		
		for(String nombreAnime : listaAnimes.keySet()) {
			
		    System.out.println(nombreAnime);
		    int numPersonajes = 0;
		    
		    for(String nombrePersonaje: listaPersonajes.keySet()) {
		    	
		    	if(listaAnimes.get(nombreAnime)==listaPersonajes.get(nombrePersonaje)) {
		    		
		    		System.out.println(" - " + nombrePersonaje);
		    		numPersonajes++;
		    	}
		    }
		    if(numPersonajes <= 0)
	    		System.out.println(" - No hay personajes");
		}
		
		//System.out.println("Personajes sin anime");
		
		int personajesSinAnime = 0;
		
		for(String nombrePersonaje: listaPersonajes.keySet()) {
			
			boolean animeEncontrado = false;
			
			for(String nombreAnime: listaAnimes.keySet()) {
				
				if(listaPersonajes.get(nombrePersonaje)==listaAnimes.get(nombreAnime))
					animeEncontrado = true;
			}
			
			if(animeEncontrado == false) {
				
				personajesSinAnime++;
				
				if(personajesSinAnime <= 1)
					System.out.println("Personajes sin anime");
			
				System.out.println(" - " + nombrePersonaje);
			}
				
		}
		
	}
	
	public static HashMap<String, Integer> leerFichero(String ruta) {
		
		HashMap<String, Integer> lista = new HashMap<>();
		
		try {
			File fichero = new File(ruta);
			Scanner lector = new Scanner(fichero);
			String linea;
			
			while(lector.hasNextLine()) {
				linea=lector.nextLine();
				int pos = linea.indexOf(" ");
				String numero = linea.substring(0, pos);
				String nombre = linea.substring(pos + 1);
				
				try {
					lista.put(nombre, Integer.parseInt(numero));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
				
			lector.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}

}
