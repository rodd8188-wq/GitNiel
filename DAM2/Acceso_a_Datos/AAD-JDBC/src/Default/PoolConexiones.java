package Default;

import java.sql.Connection;
import java.sql.SQLException;

// para usar los pool de conexiones de Apache descargamos las librerías de aquí:
// https://commons.apache.org/proper/commons-pool/download_pool.cgi
// https://commons.apache.org/proper/commons-dbcp/download_dbcp.cgi
// https://commons.apache.org/proper/commons-logging/download_logging.cgi
// descomprimimos y extraemos los jar principales
// los añadimos a nuestro build path (properties->java build path->libraries->classpath->add external jars
import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConexiones {
	
	private static BasicDataSource dataSource;
	
	// esto es un bloque de inicialización estática
	// nos garantiza que se ejecuta solo una vez, en el momento en que Java lee la clase
	// por primera vez y antes de crear ningún objeto de la misma, incluso
	// es parecido a un Singleton (un patrón de diseño muy conocido) pero no exactamente...
    static {
    	// creamos el objeto y definimos la conexión
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dam2");
        dataSource.setUsername("profe");
        dataSource.setPassword("abc123");
        
        // Configuración del pool
        dataSource.setInitialSize(5); // Creamos 5 conexiones iniciales que permanecen a la espera
        dataSource.setMaxTotal(20);   // No podrán existir mas de 20 conexiones simultaneas. Si pedimos una mas queda a la espera de que se libere alguna
        dataSource.setPoolPreparedStatements(true); // Activa el almacenamiento en caché de sentencias
        dataSource.setMaxOpenPreparedStatements(50); // Define cuántas sentencias preparadas se guardarán en caché por cada conexión.
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
