package Procesos;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) throws Exception{
		
		//Que te pida una URL y te la ejecute en firefox
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca la web: ");
		String web = sc.nextLine();
		abrirWebFirefox(web);
		//
		
		

		
		sc.close();
	}
	
	public static void abrirWebFirefox(String web) throws Exception{
				
				ProcessBuilder processB = new ProcessBuilder("firefox", web);
				
				File directorio = new File("/home/alumno");
				processB.directory(directorio);
				
				Process process = processB.start();
	}

}
