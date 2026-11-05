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
	
	
	listarProductos(emf, em);
	
	
	em.close();
	emf.close();
	}
	
	public static void crearProductos(EntityManagerFactory emf, EntityManager em) {
		
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
	
	public static void listarProductos(EntityManagerFactory emf, EntityManager em) {
		
		try {
			
			String jpql = "FROM Producto";
			
			String jpql2 = "FROM Producto p WHERE p.precio > 20";
			
			List<Producto> lista = em.createQuery(jpql2,Producto.class).getResultList();
			
			for(Producto p: lista)
				System.out.println(p);
			
			System.out.println("Total de productos: " + lista.size());
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
}