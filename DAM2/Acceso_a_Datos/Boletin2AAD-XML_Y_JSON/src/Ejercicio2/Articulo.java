package Ejercicio2;

public class Articulo {
    private String referencia;
    private String descripcion;
    private int stock;

    public Articulo(String referencia, String descripcion, int stock) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Getters (opcionales para Gson, pero recomendables)
    public String getReferencia() { 
    	return referencia;
    }
    public String getDescripcion() {
    	return descripcion;
    }
    public int getStock() {
    	return stock;
    }
}
