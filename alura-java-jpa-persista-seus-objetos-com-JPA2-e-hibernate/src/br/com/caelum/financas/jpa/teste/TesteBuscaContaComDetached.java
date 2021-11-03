package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteBuscaContaComDetached {
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		//buscando a conta de id = 1 
		//devolve uma conta c status managed - q automaticamente os dados dessa entidade sao sincronizados c os dados q estao no bd
		//qq alteracao q eu fizer, alterarah no bd
		
		ContaJPA conta = em.find(ContaJPA.class, 1);
		//a partir de agora, conta eh gerenciada pelo bd pelo hibernate
		
		conta.setTitular("Joao e o Pe de feijao");
		
		System.out.println(conta.getTitular());
		
		em.getTransaction().commit();
		
		//o estado managed ficará ate fecharmos o EntityManager
		em.close();
		//a partir de agora, obj conta deixa de ser gerenciado pelo bd pelo hibernate e vai para o estado DETACHED
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		
		em2.getTransaction().begin();
		
		
		conta.setTitular("Pedrao");
		//qdo a conta foi criada antes de ficar no estado managed e o entitymanager foi fechado por algum motivo, a conta fica no estado
		// DETACHED. para ficar no estado managed, precisa chamar o metodo merge. n vai aceitar o persist, pq estah detached
		//metodo merge= DETACHED -> MANAGED		
		em2.merge(conta);
		
		em2.getTransaction().commit();
		
		//o estado managed ficará ate fecharmos o EntityManager. depois q fecha o em, o estado vai para DETACHED
		em2.close();
		
		
		
	}

}
