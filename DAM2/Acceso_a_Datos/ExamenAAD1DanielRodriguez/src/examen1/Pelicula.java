package examen1;

import java.util.ArrayList;

public class Pelicula {
	
	public static ArrayList<Pelicula> peliculas = new ArrayList<>();
	
	private String titulo;
	private int anio;
	private int fase;
	private String director;
	
	public Pelicula(String titulo, int anio, int fase, String director) {
		this.titulo=titulo;
		this.anio=anio;
		this.fase=fase;
		this.director=director;
		
		peliculas.add(this);
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	public int getAnio() {
		return this.anio;
	}
	public int getFase() {
		return this.fase;
	}
	public String getDirector() {
		return this.director;
	}
}
