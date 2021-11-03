package br.com.caelum.financas.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	/**
	 * 
	 financas eh o nome do persistence-unit definido no persistence.xml
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

}
