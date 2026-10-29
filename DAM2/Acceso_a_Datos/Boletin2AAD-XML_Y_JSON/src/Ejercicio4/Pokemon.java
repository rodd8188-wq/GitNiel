package Ejercicio4;

//Clase que representa un Pokémon. Gson usará los nombres de los atributos
//como claves del JSON, y sus tipos para decidir si el valor lleva comillas o no.
public class Pokemon {
 private int codigo;        // número -> sin comillas en el JSON
 private String nombre;     // texto  -> con comillas
 private double peso;       // decimal -> sin comillas
 private double altura;
 private String tipo;       // "Bicho, Veneno" si hay dos tipos

 public Pokemon(int codigo, String nombre, double peso, double altura, String tipo) {
     this.codigo = codigo;
     this.nombre = nombre;
     this.peso = peso;
     this.altura = altura;
     this.tipo = tipo;
 }

 public int getCodigo() {
	 return codigo;
 }
 public String getNombre() {
	 return nombre;
 }
 public double getPeso() {
	 return peso;
 }
 public double getAltura() {
	 return altura;
 }
 public String getTipo() {
	 return tipo;
 }
}