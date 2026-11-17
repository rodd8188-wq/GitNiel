package VisualVM;

public class Prueba {

    // Definición de una clase genérica
    static class Caja<T> {
        private T contenido;

        public void guardar(T contenido) {
            this.contenido = contenido;
        }

        public T obtener() {
            return contenido;
        }
    }

    // Clase principal con el método main
    public static class Main {
        public static void main(String[] args) throws InterruptedException {
            while (true) {
                // Caja que almacena un String
                Caja<String> cajaDeTexto = new Caja<>();
                cajaDeTexto.guardar("Hola, código genérico");
                System.out.println("Contenido (String): " + cajaDeTexto.obtener());

                // Caja que almacena un Integer

                Caja<Integer> cajaDeNumero = new Caja<>();
                cajaDeNumero.guardar(100);
                System.out.println("Contenido (Integer): " + cajaDeNumero.obtener());
            }
        }
    }

}

