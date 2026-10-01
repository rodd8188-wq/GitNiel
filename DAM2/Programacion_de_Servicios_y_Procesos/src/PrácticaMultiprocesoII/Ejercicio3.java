package PrácticaMultiprocesoII;

public class Ejercicio3 {
    public static void main(String[] args) {
        String texto = args[0];
        String alReves = new StringBuilder(texto).reverse().toString();

        if (texto.equals(alReves)) {
            System.out.println("Es palíndromo");
        } else {
            System.out.println("NO es palíndromo");
        }
        System.exit(0);
    }
}