package com.oberdan.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jsf-basics-oracle");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void closeEntityManager(EntityManager em) {
		em.close();
	}
	
}
