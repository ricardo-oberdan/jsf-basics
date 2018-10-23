package com.oberdan.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jsf-basics-oracle");
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager em) {
		em.close();
	}
	
}
