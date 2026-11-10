
package PracticaMultiprocesoIII;

import java.util.Scanner;

public class Ejercicio3Hijo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String cadena = "";

        while (sc.hasNextLine()) {
            cadena += sc.nextLine();
        }

        String reversa = "";
        

        for (int i = cadena.length() - 1; i >= 0; i--) {
            reversa += cadena.charAt(i);
        }

        if (cadena.equals(reversa)) {
            System.out.println(0);
        } else {
            System.out.println(-1);
        }
        
        sc.close();
    }
}
