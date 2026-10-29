package Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	// Datos de conexión: ajústalos a tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String USER = "root";
    private static final String PASSWORD = "";
 
    /**
     * Lista los empleados de la oficina situada en la ciudad indicada.
     *
     * @param ciudad nombre de la ciudad a consultar (por ejemplo, "Paris")
     */
    public static void listarEmpleadosPorCiudad(String ciudad) {
        // Consulta 1: obtener el código de la oficina a partir de la ciudad
        // El símbolo ? es un parámetro que se rellenará después (evita inyección SQL)
        String sqlOficina = "SELECT officeCode FROM offices WHERE city = ?";
 
        // Consulta 2: obtener los empleados de una oficina concreta
        String sqlEmpleados = "SELECT lastName, firstName, email FROM employees WHERE officeCode = ?";
 
        // try-with-resources: la conexión y el statement se cierran solos al terminar
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psOficina = con.prepareStatement(sqlOficina)) {
 
            // Sustituimos el primer ? por el nombre de la ciudad recibido
            psOficina.setString(1, ciudad);
 
            // Aquí guardaremos el código de oficina; null significa "no encontrada"
            String officeCode = null;
 
            // Ejecutamos la consulta de la oficina
            try (ResultSet rsOficina = psOficina.executeQuery()) {
                // Si hay una fila, la oficina existe y recogemos su código
                if (rsOficina.next()) {
                    officeCode = rsOficina.getString("officeCode");
                }
            }
 
            // Caso 1: no existe oficina en esa ciudad
            if (officeCode == null) {
                System.out.println("No existe oficina en " + ciudad);
                return; // Terminamos la función aquí
            }
 
            // Lista donde guardaremos cada empleado ya formateado como texto
            List<String> empleados = new ArrayList<>();
 
            // Preparamos la consulta de empleados con el código de oficina obtenido
            try (PreparedStatement psEmp = con.prepareStatement(sqlEmpleados)) {
                psEmp.setString(1, officeCode);
 
                try (ResultSet rs = psEmp.executeQuery()) {
                    // Recorremos todas las filas devueltas
                    while (rs.next()) {
                        // Formato pedido: Apellido, Nombre (email)
                        empleados.add(rs.getString("lastName") + ", "
                                + rs.getString("firstName")
                                + " (" + rs.getString("email") + ")");
                    }
                }
            }
 
            // Caso 2: la oficina existe pero no tiene empleados
            if (empleados.isEmpty()) {
                System.out.println("La oficina de " + ciudad + " no tiene empleados");
                return;
            }
 
            // Caso 3: resultado normal. Mostramos el total y luego cada empleado
            System.out.println("Hay " + empleados.size() + " empleados en la oficina de "
                    + ciudad + ". Sus datos son:");
            for (String e : empleados) {
                System.out.println(e);
            }
 
        } catch (SQLException ex) {
            // Cualquier error de conexión o de consulta se muestra por la salida de error
            System.err.println("Error de base de datos: " + ex.getMessage());
        }
    }
 
    /**
     * Método principal con dos pruebas: una ciudad con oficina y otra sin ella.
     */
    public static void main(String[] args) {
        listarEmpleadosPorCiudad("Paris");   // Debería listar 5 empleados
        listarEmpleadosPorCiudad("Madrid");  // Debería indicar que no existe oficina
    }

}
