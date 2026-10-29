package Default;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databases6 {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/dam2";
		String usuario = "profe";
		String password = "abc123";

		try(Connection conn = DriverManager.getConnection(jdbcURL, usuario, password)) {
			// Los commit tienen que ser manuales
			conn.setAutoCommit(false);
			
			System.out.println("Conexión realizada correctamente");

			Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM alumnos";

			ResultSet res = sql.executeQuery(query);
			res.first();

			res.updateString("nombre", "Juan");
			res.updateRow();
			
			res = sql.executeQuery("SELECT * FROM alumnos");
			while (res.next())
				System.out.println(res.getString("nombre") + " - " + res.getString("email"));
			
			// si no ejecutamos commit los cambios en la base de datos no se guardan
			// aunque los hayamos visto en el listado anterior
			// pero al cerrar la base de datos se descartan y se vuelve a lo anterior
			// conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
