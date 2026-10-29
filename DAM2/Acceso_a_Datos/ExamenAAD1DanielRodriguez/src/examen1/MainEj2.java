package examen1;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainEj2 {

	public static void main(String[] args) {
		
		String entrada = "/home/alumno/gitNiel/GitNiel/DAM2/Acceso_a_Datos/ExamenAAD1DanielRodriguez/Ficheros/marvel.json";
		
		leerJSON(entrada);
		
		baseDeDatosTest();
		
		//listarPeliculasTest();
		
	}
	
	public static void leerJSON(String entrada) {
		try {
			
			File fichero = new File(entrada);
			Scanner lector = new Scanner(fichero);
			String linea;
			String[] palabras;
			
			lector.nextLine();
			lector.nextLine();
			while(lector.hasNextLine()) {
				linea=lector.nextLine();
				linea = linea.replace("{", "").replace("}", "").trim();
				palabras = linea.split(",");
				
				ArrayList<String> atributo = new ArrayList<>();
				
				//Pelicula
				
				for(int i=0; i<palabras.length;i++) {
					///1 pasa por atributo
					int pos = palabras[i].indexOf(":");
					palabras[i] = palabras[i].substring(pos+1).replace("]", "");
					atributo.add(palabras[i].trim().replace("\"", ""));
				}
				
				new Pelicula(atributo.get(0), Integer.parseInt(atributo.get(1)), Integer.parseInt(atributo.get(2)), atributo.get(3));
				///1 pasada por pelicula
			}
			
			
			
			lector.close();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}
	
	public static void listarPeliculasTest() {
		System.out.println("Lista peliculas:");
		for(Pelicula pelicula: Pelicula.peliculas)
			System.out.println(" - "+pelicula.getTitulo()+", "+pelicula.getAnio()+", "+pelicula.getFase()+", "+pelicula.getDirector());
	}
	
	public static void baseDeDatosTest() {
		String jdbcURL = "jdbc:mysql://localhost:3306";
		String usuario = "profe";
		String password = "abc123";

		try(Connection conn = DriverManager.getConnection(jdbcURL, usuario, password)) {
			System.out.println("Conexión realizada correctamente");
			
			//los query
			String query1 = "CREATE DATABASE IF NOT EXISTS examen";
			String query2 = "USE examen";
			String query3 = "CREATE TABLE IF NOT EXISTS peliculas (titulo VARCHAR(50) PRIMARY KEY, anio INT(5), fase INT(2), director VARCHAR(50)";
			
			//Ejecutarlos
			Statement consulta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			consulta.executeUpdate(query1);
			consulta.executeUpdate(query2);
			consulta.executeUpdate(query3);
			
			for(Pelicula pelicula: Pelicula.peliculas) {

				PreparedStatement query = conn.prepareStatement(
						"INSERT INTO peliculas VALUES ("+ pelicula.getTitulo() + " , "+ pelicula.getAnio() + ", "+ pelicula.getFase() + ", "+ pelicula.getDirector() + ")");
				query.executeUpdate();
			}
			
			
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerTablaPeliculas() {
		String jdbcURL = "jdbc:mysql://localhost:3306";
		String usuario = "profe";
		String password = "abc123";

		try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, password)) {
			
			PreparedStatement query = conexion.prepareStatement("SELECT * FROM peliculas ORDER BY anio ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = query.executeQuery();
			
			resultado.beforeFirst();
			while(resultado.next()) {
				System.out.printf("%s (Fase %s) - %s [%s]\n",resultado.getInt("anio"), resultado.getInt("fase"), resultado.getString("titulo"), resultado.getString("director"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

}
