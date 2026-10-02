package PrácticaMultiprocesoIII;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class LlamarEjercicio1 {
	
	public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Escribe un número entero positivo: ");
        String numero = teclado.nextLine();

        try {

            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "Ejercicio1");
            
            File fileInput = new File("DatoEntrada");
            File fileOutput = new File("DatoSalida");
            File fileError = new File("DatoError");
            
            FileWriter fw = new FileWriter(numero);

            Process proceso = pb.start();

            int salida = proceso.waitFor();
            
            System.out.println(salida);

            if (salida == 253) {
                System.out.println("Has escrito un entero positivo");

            } else if (salida == 254) {
                System.out.println("No has escrito un entero");

            } else if (salida == 255) {
                System.out.println("El argumento está vacío");

            } else {
                System.out.println("El entero debe ser positivo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        teclado.close();
        
    }	//main
    
}