package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	// Atributo estático para deshabilitar el logger de hibernate (Los mensajes en
	// ROJO) y junto a esta linea esto: LOG_HIBERNATE.setLevel(Level.SEVERE);
	private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");

	public static void main(String[] args) {

		// Le decimos a hibernate que solo muestre los errores y no mensajes informativos
		LOG_HIBERNATE.setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clasePU");
		EntityManager em = emf.createEntityManager();
		
		listarEmpleados(em);

		em.close();
		emf.close();

	}

	public static void listarEmpleados(EntityManager em) {

		try {
			
			String jpql = "SELECT e.lastName, e.firstName, e.email FROM Employees e";
			
			//String jpql = "SELECT p.nombre, p.precio FROM Producto p";
			
			List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
			
			for (Object[] e : lista)
				System.out.println((String) e[0] + ", " + (String) e[1] + " - " + (String) e[2]);
				//System.out.println((String) e[0] + " - " + (String) e[1]);
			
			System.out.println("Total de empleados: " + lista.size());
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
