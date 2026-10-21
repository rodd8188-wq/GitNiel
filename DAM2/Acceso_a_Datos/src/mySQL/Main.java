package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("SQL:");
		try {
			Connection conn = PoolConexiones.getConnection();
			System.out.println("Conexión Exitosa");
			//Desactivamos el autocommit
			conn.setAutoCommit(false);
			
			mostrarTodo(conn);
			
			//Aplicamos un commit para que se ejecuten los cambios
			conn.commit();
			
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();			//Muestra el error completo
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void mostrarTodo(Connection conn) throws Exception{
		
		Statement sql = conn.createStatement();
		ResultSet resultado = sql.executeQuery("SELECT * FROM alumnos");
		
		while(resultado.next()) {
			System.out.printf("%s %s %s %s\n", resultado.getString(1), resultado.getString(2), resultado.getInt(3), resultado.getString(4));
		}
	}//	mostrarTodo
	
	public static void borrarPorCorreo(Connection conn) throws Exception{
		
		Statement sql = conn.createStatement();
		sql.executeUpdate("DELETE FROM alumnos WHERE email='ana@educa.madrid.org'");
	}//

}
