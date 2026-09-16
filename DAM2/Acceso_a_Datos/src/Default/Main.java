package Default;

import java.io.File;
import java.nio.file.Path;

public class Main {

	private static final String DIR_CONFG = "DAM2" + File.separator + "daniel";
	
	public static void main (String[] args) {
		
		/// Obtener el directorio actual
		File directorioActual = new File(".");
		System.out.println(directorioActual.getAbsolutePath());
		
		File dirConfg = new File(DIR_CONFG);
		
		///Comprobar si el directorio existe y crea el directorio
		if(dirConfg.exists())
			System.out.println("El directorio " + dirConfg + " existe.");
		else {
			System.out.println("El directorio " + dirConfg + " no existe.");
			//dirConfg.mkdir();		//No puede crear más de 1 directorio a la vez
			dirConfg.mkdirs();		//Puede crear más de 1 direcctorio a la vez
		}
		
		try {
			
			File fichero = new File("/home/alumno/config");
			
			Path fichero2 = Path.of("/home/alumno/metodo1");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println(directorioActual.getAbsolutePath().substring(0, directorioActual.getAbsolutePath().length() -1) + DIR_CONFG);
		
	}

}
