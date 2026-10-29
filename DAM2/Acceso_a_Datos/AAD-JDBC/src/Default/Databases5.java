package Default;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databases5 {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/dam2";
		String usuario = "profe";
		String password = "abc123";

		// También podemos usar un try-with-resources
		try(Connection conn = DriverManager.getConnection(jdbcURL, usuario, password)) {
			System.out.println("Conexión realizada correctamente");

			Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM alumnos";

			ResultSet res = sql.executeQuery(query);
			res.first();

			res.updateString("nombre", "Pepe");
			res.updateRow();
			
			res = sql.executeQuery("SELECT * FROM alumnos");
			while (res.next())
				System.out.println(res.getString("nombre") + " - " + res.getString("email"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
