package PrácticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LlamarEjercicio2 {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "Ejercicio2");
        Process proceso = pb.start();

        BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));

        String linea = "";
        while (!linea.equals("*")) {
            System.out.println("Escribe un número:");
            linea = teclado.nextLine();
            escritor.write(linea);
            escritor.newLine();
        }
        escritor.close();

        int salida = proceso.waitFor();
        System.out.println("Valor de Salida: " + salida);

        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String resultado;
        while ((resultado = lector.readLine()) != null) {
            System.out.println(resultado);
        }
    }
}