package examen1;

public class PokemonEj1 {
	
	private int id;
	private int nivel;
	private String nombre;
	private String[] tipos;
	
	public PokemonEj1(int id, int nivel, String nombre, String tipo) {
		this.id=id;
		this.nivel=nivel;
		this.nombre=nombre;
		this.tipos[0]=tipo;
	}
	
	public PokemonEj1(int id, int nivel, String nombre, String tipo1, String tipo2) {
		this.id=id;
		this.nivel=nivel;
		this.nombre=nombre;
		this.tipos[0]=tipo1;
		this.tipos[1]=tipo2;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String[] getTipo() {
		return this.tipos;
	}
}