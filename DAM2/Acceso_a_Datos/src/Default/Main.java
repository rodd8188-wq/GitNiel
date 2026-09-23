package Default;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

public class Main {

	private static final String DIR_CONFG = "DAM2" + File.separator + "daniel";
	private static final String ARCHIVO_CONFIG = DIR_CONFG + File.separator + "config.txt";
	
	public static void main (String[] args) {
		
		//clase1();
		
		clase2();
		
		
	}
	
	public static void clase1() {
		try {
			
		/// Obtener el directorio actual
			File directorioActual = new File(".");
			System.out.println(directorioActual.getAbsolutePath());
			
			File dirConfg = new File(DIR_CONFG);
			
			boolean crearFichero = true;
			
			///Comprobar si el directorio existe y crea el directorio
			if(dirConfg.exists())
				System.out.println("El directorio " + dirConfg + " existe.");
			else {
				System.out.println("El directorio " + dirConfg + " no existe.");
				//dirConfg.mkdir();		//No puede crear más de 1 directorio a la vez
				//dirConfg.mkdirs();		//Puede crear más de 1 direcctorio a la vez
				if(dirConfg.mkdirs() == false) {

					System.out.println("No se ha podido crear el directorio");
				}
			}
			
			if(crearFichero) {
				/// Crear un fichero si no existe
				FileWriter fichero = new FileWriter(ARCHIVO_CONFIG);
				
				File archivo = new File(ARCHIVO_CONFIG);
				if(archivo.createNewFile())
					System.out.println("Archivo creado");
				else
					System.out.println("Archivo no creado");
			}
			
			//Nos devuelve el espacio libre en bytes
			long espacio = dirConfg.getFreeSpace() / 1024 / 1024 / 1024;
			System.out.println("Espacio libre en disco: " + espacio + " GB");
				
			System.out.println(directorioActual.getAbsolutePath().substring(0, directorioActual.getAbsolutePath().length() -1) + DIR_CONFG);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	//clase1
	
	public static void clase2() {
		
		
		
	}	//clase2
	
	
	
	
	
}	//fin main
