package com.jpa.EmpresaNiel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	// Atributo estático para deshabilitar el logger de hibernate (Los mensajes en ROJO) y junto a esta linea esto: LOG_HIBERNATE.setLevel(Level.SEVERE);
	private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");
		
	public static void main(String[] args) {
		
		// Le decimos a hibernate que solo muestre los errores y no mensajes informativos
		LOG_HIBERNATE.setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clasePU");	// Con el nombre del 'persistence-unit' en el 'persistence.xml'
		EntityManager em = emf.createEntityManager();
		
		//crearProductos(em);
		
		//listarProductos(em);
		
		//listarNombresProductos(em);
		
		//listarNombresPreciosProductos(em);
		
		mostrarConsultaParametrizada(em);
		
		em.close();
		emf.close();
		}
		
		public static void crearProductos(EntityManager em) {
			
			try {
				
				em.getTransaction().begin();
				
				Producto p = new Producto("Teclado", 25.5);
				em.persist(p);			// Escribimos en la base de datos
				
				p = new Producto("Raton gamer", 15.55);
				em.persist(p);
				
				p = new Producto("Teclado mecanico", 27.35);
				em.persist(p);
				
				em.persist(new Producto("Monitor 21\"", 90.85));
				
				em.getTransaction().commit();	// Lo guardamos en la base de datos
				
				//System.out.println("Producto guardado con id " + p.getId());
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				em.getTransaction().rollback();		//Cancelamos los cambios
			}
			
		}
		
		public static void listarProductos(EntityManager em) {
			
			try {
				
				//String jpql = "FROM Producto";
				
				String jpql = "FROM Producto p WHERE p.precio > 20";
				
				List<Producto> lista = em.createQuery(jpql,Producto.class).getResultList();
				
				for(Producto p: lista)
					System.out.println(p);
				
				System.out.println("Total de productos: " + lista.size());
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
		
		public static void listarNombresProductos(EntityManager em) {
			
			try {
				
				String jpql = "SELECT p.nombre FROM Producto p";
				
				List<String> lista = em.createQuery(jpql,String.class).getResultList();
				
				for(String p: lista)
					System.out.println(p);
				
				System.out.println("Total de productos: " + lista.size());
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
		
		public static void listarNombresPreciosProductos(EntityManager em) {
			
			try {
				
				String jpql = "SELECT p.nombre, p.precio FROM Producto p";
				
				List<Object[]> lista = em.createQuery(jpql,Object[].class).getResultList();
				
				for(Object[] p: lista)
					System.out.println((String)p[0] + " - " + (Double)p[1]);
				
				System.out.println("Total de productos: " + lista.size());
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
		
		public static void mostrarConsultaParametrizada(EntityManager em) {
			
			try {
				
				String jpql = "FROM Producto p WHERE p.precio > :precioMax AND p.nombre LIKE ?1";
				
				List<Producto> lista = em.createQuery(jpql,Producto.class)
						.setParameter(1, "Tec%")
						.setParameter("precioMax", 20)
						.getResultList();
				
				for(Producto p: lista)
					System.out.println(p);
				
				System.out.println("Total de productos: " + lista.size());
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
}