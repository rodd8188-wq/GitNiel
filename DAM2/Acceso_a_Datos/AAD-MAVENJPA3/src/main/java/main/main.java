package main;

import model.*;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class main {

	private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");

	public static void main(String[] args) {

		LOG_HIBERNATE.setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clasePU");
		EntityManager em = emf.createEntityManager();
		try {
			listadoNombresPaises(em);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		em.close();
		emf.close();

	} // main

	public static void listadoNombresPaises(EntityManager em) {
		String jpql = "SELECT pais.name FROM Countries pais";
		List<String> listaNombres = em.createQuery(jpql, String.class).getResultList();
		System.out.println("--- Nombres de todos los paises ---");
		for (String nombre : listaNombres) {
			System.out.println(nombre);
		}
		System.out.println("Total de paises: " + listaNombres.size());
	}

	public static void verIdiomas(EntityManager em, String pais) {
		String jpql = "FROM Countries WHERE name = :pais";
		List<Countries> listaPaises = em.createQuery(jpql, Countries.class).setParameter("pais", pais).getResultList();
		if (listaPaises.isEmpty())
			System.out.println("No existe ese país");
		else if (listaPaises.size() != 1)
			System.out.println("Hay mas de un país con el mismo nombre");
		else {
			Countries miPais = (Countries) listaPaises.get(0);
			Set<CountryLanguages> listaIdiomas = miPais.getCountryLanguageses();
			if (listaIdiomas.size() != 0) {
				System.out.println("--- Idiomas de " + pais + " ---");
				for (CountryLanguages fila : listaIdiomas)
					System.out.println(fila.getLanguages().getLanguage());
			} else
				System.out.println("No hay idiomas para " + pais);
		}
	}
	
	private static void paisesDeUnContinente(EntityManager em, String continente) {
		String jpql = "SELECT c FROM Countries c "
				+ "JOIN c.regions r "
				+ "JOIN r.continents co "
				+ "WHERE co.name = :continente "
				+ "ORDER BY c.name";
		List<Countries> paises = em.createQuery(jpql, Countries.class).setParameter("continente", continente)
				.getResultList();
		System.out.println("--- Paises de " + continente + " ---");
		for (Countries pais : paises) {
			System.out.println(pais.getName());
		}
	}

} // class
