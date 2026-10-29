package examen1;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class MainEj1 {

	public static void main(String[] args) {
		
		String entrada = "/home/alumno/gitNiel/GitNiel/DAM2/Acceso_a_Datos/ExamenAAD1DanielRodriguez/Ficheros/pokedex.xml";
		String salida = "/home/alumno/gitNiel/GitNiel/DAM2/Acceso_a_Datos/ExamenAAD1DanielRodriguez/Ficheros/pokedex.txt";
		
		try {
			
			ficheroXMLaTXT(entrada, salida);
			
		} catch (Exception e) {
			
		}
		
	}// fin main
	
	public static void ficheroXMLaTXT(String entrada, String salida) {
		
		try {
			File fichero = new File(entrada);
			Scanner lector = new Scanner(fichero);
			String linea;

			
			lector.nextLine();
			lector.nextLine();
			while(lector.hasNextLine()) {
				linea=lector.nextLine();
				
				
				
				System.out.println(linea);
			}
				
			lector.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}//	fin class
