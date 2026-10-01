package PrácticaMultiprocesoII;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LlamarEjercicio3 {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escribe un texto:");
        String texto = teclado.nextLine();

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "Ejercicio3", texto);
        Process proceso = pb.start();

        int salida = proceso.waitFor();
        System.out.println("Valor de Salida: " + salida);

        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String resultado;
        while ((resultado = lector.readLine()) != null) {
            System.out.println(resultado);
        }
    }
}