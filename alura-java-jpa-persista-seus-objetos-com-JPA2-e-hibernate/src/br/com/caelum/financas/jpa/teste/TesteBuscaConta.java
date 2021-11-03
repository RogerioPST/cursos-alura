package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteBuscaConta {
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		//buscando a conta de id = 1 
		//devolve uma conta c status managed - q automaticamente os dados dessa entidade sao sincronizados c os dados q estao no bd
		//qq alteracao q eu fizer, alterarah no bd
		ContaJPA conta = em.find(ContaJPA.class, 1);
		
		conta.setTitular("Joao e o Pe de feijao");
		
		System.out.println(conta.getTitular());
		
		em.getTransaction().commit();
		
		//o estado managed ficará ate fecharmos o EntityManager
		em.close();
	}

}
