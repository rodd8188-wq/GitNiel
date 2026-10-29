package Ejercicio6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	// Datos de conexión: ajústalos a tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/pokemondb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    /*
     * Pruebas de los distintos casos.
     */
    public static void main(String[] args) {
        listarPorTipo("Dragón");    // Varios pokemon, uno con tipo adicional
        listarPorTipo("Profesor");  // Tipo que no existe
        listarPorTipo("Acero");     // No existe en la 1ª generación, por lo que dará "no reconocido"
    }
    
    /**
     * Lista los pokemon del tipo indicado.
     *
     * @param tipo nombre del tipo (por ejemplo, "Dragón")
     */
    public static void listarPorTipo(String tipo) {
        // Consulta 1: comprobar que el tipo existe y obtener su nombre y su id
        String sqlTipo = "SELECT id_tipo, nombre FROM tipo WHERE nombre = ?";
 
        // Consulta 2: pokemon que tienen el tipo buscado (pt1) y, para cada uno,
        // todos sus tipos (pt2 + t2) concatenados en una sola cadena con GROUP_CONCAT
        String sqlPokemon =
                "SELECT p.numero_pokedex, p.nombre, p.peso, p.altura, "
              + "       GROUP_CONCAT(t2.nombre ORDER BY t2.id_tipo SEPARATOR ', ') AS tipos "
              + "FROM pokemon p "
              + "JOIN pokemon_tipo pt1 ON pt1.numero_pokedex = p.numero_pokedex AND pt1.id_tipo = ? "
              + "JOIN pokemon_tipo pt2 ON pt2.numero_pokedex = p.numero_pokedex "
              + "JOIN tipo t2 ON t2.id_tipo = pt2.id_tipo "
              + "GROUP BY p.numero_pokedex, p.nombre, p.peso, p.altura "
              + "ORDER BY p.numero_pokedex";
 
        // try-with-resources: la conexión se cierra automáticamente al terminar
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
 
            int idTipo;          // Identificador del tipo en la base de datos
            String nombreTipo;   // Nombre del tipo tal y como está guardado
 
            // --- Comprobamos que el tipo existe ---
            try (PreparedStatement ps = con.prepareStatement(sqlTipo)) {
                ps.setString(1, tipo);
                try (ResultSet rs = ps.executeQuery()) {
                    // Caso 1: el tipo no existe
                    if (!rs.next()) {
                        System.out.println(tipo + " no es un tipo reconocido en esta Pokedex");
                        return;
                    }
                    idTipo = rs.getInt("id_tipo");
                    nombreTipo = rs.getString("nombre");
                }
            }
 
            // --- Buscamos los pokemon de ese tipo ---
            try (PreparedStatement ps = con.prepareStatement(sqlPokemon)) {
                ps.setInt(1, idTipo);
                try (ResultSet rs = ps.executeQuery()) {
 
                    // Caso 2: el tipo existe pero no hay ningún pokemon con él
                    if (!rs.next()) {
                        System.out.println("No existe ningún Pokemon de tipo " + nombreTipo
                                + " en esta Pokedex");
                        return;
                    }
 
                    System.out.println("Los pokemon de tipo " + nombreTipo + " son:");
 
                    // Caso 3: mostramos cada pokemon. Usamos do-while porque
                    // el primer next() ya nos ha situado en la primera fila.
                    do {
                        System.out.println(rs.getInt("numero_pokedex") + " – "
                                + rs.getString("nombre")
                                + ". Peso: " + rs.getDouble("peso")
                                + ". Altura: " + rs.getDouble("altura")
                                + " Tipos: " + rs.getString("tipos"));
                    } while (rs.next());
                }
            }
 
        } catch (SQLException ex) {
            // Cualquier error de conexión o de consulta se muestra por la salida de error
            System.err.println("Error de base de datos: " + ex.getMessage());
        }
    }

}
