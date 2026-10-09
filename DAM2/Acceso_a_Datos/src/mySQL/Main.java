package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		String
			//url = "jdbc:mysql://localhost:3306/",		//Especificamos el puerto
			//url = "jdbc:mysql://localhost/dam2",		//Especificamos la base
			//url = "jdbc:mysql://localhost/",			//Nos conectamos solo a la maquina
			url = "jdbc:mysql://localhost/dam2",
			usuario = "dan",
			password = "abc123";
		
		System.out.print("SQL:");
		try(Connection conn = DriverManager.getConnection(url, usuario, password);) {
			System.out.println("Conexión Exitosa");
			
			Statement sql = conn.createStatement();
			ResultSet resultado = sql.executeQuery("SELECT * FROM alumnos");
			
			while(resultado.next()) {
				System.out.printf("%s %s %s %s\n", resultado.getString(1), resultado.getString(2), resultado.getInt(3), resultado.getString(4));
			}
			
			
			//conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();			//Muestra el error completo
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
