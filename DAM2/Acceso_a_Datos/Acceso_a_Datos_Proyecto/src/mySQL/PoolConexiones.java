package mySQL;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConexiones {
	
	private static BasicDataSource dataSource;
	
	static {
		
		dataSource = new BasicDataSource();
		
		String
			url = "jdbc:mysql://localhost/dam2",
			usuario = "dan",
			password = "abc123";
		
		dataSource.setUrl(url);
		dataSource.setUsername(usuario);
		dataSource.setPassword(password);
		
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(100);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxOpenPreparedStatements(50);
		
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}//class
