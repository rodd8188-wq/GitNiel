package Ejercicio3;

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
 
    /**
     * Muestra las evoluciones posibles y la preevolución del pokemon indicado.
     *
     * @param nombre nombre del pokemon (por ejemplo, "Bulbasaur")
     */
    public static void mostrarEvoluciones(String nombre) {
        // Consulta 1: datos del pokemon a partir de su nombre
        String sqlPokemon = "SELECT numero_pokedex, nombre FROM pokemon WHERE nombre = ?";
 
        // Consulta 2: pokemon en los que evoluciona (el pokemon actual es el ORIGEN)
        String sqlEvoluciones =
                "SELECT p.numero_pokedex, p.nombre "
              + "FROM evoluciona_de e JOIN pokemon p ON p.numero_pokedex = e.pokemon_evolucionado "
              + "WHERE e.pokemon_origen = ? ORDER BY p.numero_pokedex";
 
        // Consulta 3: pokemon del que proviene (el pokemon actual es el EVOLUCIONADO)
        String sqlOrigen =
                "SELECT p.numero_pokedex, p.nombre "
              + "FROM evoluciona_de e JOIN pokemon p ON p.numero_pokedex = e.pokemon_origen "
              + "WHERE e.pokemon_evolucionado = ? ORDER BY p.numero_pokedex";
 
        // try-with-resources: la conexión se cierra automáticamente al terminar
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
 
            int numero;           // Número de pokedex del pokemon consultado
            String nombreReal;    // Nombre tal y como está guardado en la base de datos
 
            // --- Buscamos el pokemon ---
            try (PreparedStatement ps = con.prepareStatement(sqlPokemon)) {
                ps.setString(1, nombre);
                try (ResultSet rs = ps.executeQuery()) {
                    // Caso: el pokemon no existe (no está en la pokedex o no es de la 1ª generación)
                    if (!rs.next()) {
                        System.out.println("El pokemon " + nombre
                                + " no está en la pokedex o no es de la primera generación");
                        return;
                    }
                    numero = rs.getInt("numero_pokedex");
                    nombreReal = rs.getString("nombre");
                }
            }
 
            // Construimos la frase poco a poco; empieza con el nombre y el número
            StringBuilder sb = new StringBuilder();
            sb.append(nombreReal).append(" (#").append(numero).append(")");
 
            // --- Parte 1: en qué evoluciona (puede ser ninguno, uno o varios) ---
            String evoluciones = listarPokemon(con, sqlEvoluciones, numero);
            if (evoluciones.isEmpty()) {
                sb.append(" - no evoluciona en ningún otro");
            } else {
                sb.append(" - evoluciona en ").append(evoluciones);
            }
 
            // --- Parte 2: de quién proviene (ninguno o uno) ---
            String origen = listarPokemon(con, sqlOrigen, numero);
            if (origen.isEmpty()) {
                sb.append(" - no evoluciona de ningún otro");
            } else {
                sb.append(" - evoluciona de ").append(origen);
            }
 
            System.out.println(sb);
 
        } catch (SQLException ex) {
            // Cualquier error de conexión o de consulta se muestra por la salida de error
            System.err.println("Error de base de datos: " + ex.getMessage());
        }
    }
 
    /**
     * Ejecuta una consulta que recibe un número de pokedex y devuelve
     * los pokemon resultantes con el formato "Nombre (#n) Nombre (#n) ...".
     * Devuelve una cadena vacía si no hay resultados.
     */
    private static String listarPokemon(Connection con, String sql, int numero) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Separamos los pokemon con un espacio (Vaporeon (#134) Jolteon (#135) ...)
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }
                    sb.append(rs.getString("nombre"))
                      .append(" (#").append(rs.getInt("numero_pokedex")).append(")");
                }
            }
        }
        return sb.toString();
    }
 
    /**
     * Pruebas de los distintos casos.
     */
    public static void main(String[] args) {
        mostrarEvoluciones("Bulbasaur"); // Evoluciona pero no proviene de otro
        mostrarEvoluciones("Ivysaur");   // Evoluciona y proviene de otro
        mostrarEvoluciones("Venusaur");  // Proviene de otro pero no evoluciona
        mostrarEvoluciones("Mew");       // Ni evoluciona ni proviene de otro
        mostrarEvoluciones("Eevee");     // Varias evoluciones
        mostrarEvoluciones("Sylveon");   // No existe en la base de datos
    }
}
