package Ejercicio01;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Main {
	
	final static String ruta = "Documentos/coordenadas.dat";

	public static void main(String[] args) {
		
		final int TAMANYO_REGISTRO = 20;
		
		try(DataInputStream fichero = new DataInputStream(new FileInputStream(ruta))){
			System.out.println("SATÉLITES Y COORDENADAS");
			
			File ficheroFisico = new File(ruta);
			final int NUM_REGISTROS = (int)ficheroFisico.length()/TAMANYO_REGISTRO;
			
			for(int i=0; i<NUM_REGISTROS; i++) {
				int id = fichero.readInt();
				float latitud = fichero.readFloat();
				float longitud = fichero.readFloat();
				String estado = "";
				
				for(int j=0; j<4; j++)
					estado+=fichero.readChar();
				
				System.out.printf("SATÉLITE ID: %d | POSICIÓN: (%.4f,%.4f) | ESTADO: %s \n", id, latitud, longitud, estado);
			}
			
			
		} catch(Exception e) {
			
		}
		

	}

}
