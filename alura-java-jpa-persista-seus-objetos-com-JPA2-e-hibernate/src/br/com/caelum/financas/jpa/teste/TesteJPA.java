package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteJPA {

	public static void main(String[] args) {

		ContaJPA conta = new ContaJPA();
		conta.setTitular("Maria dos Santos");
		conta.setBanco("Caixa");
		conta.setAgencia("043");
		conta.setNumero("54321");

		

		/**
		 * Usando HSQLDB
		 */

		// EntityManagerFactory emf = Persistence
		// .createEntityManagerFactory("contas-hsqldb");

		/**
		 * Usando PostgreSQL
		 */
		// EntityManagerFactory emf = Persistence
		// .createEntityManagerFactory("contas-postgres");

		/**
		 * Usando MySQL financas eh o nome do persistence-unit definido no
		 * persistence.xml
		 */
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("financas");

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		//status managed - q automaticamente os dados dessa entidade sao sincronizados c os dados q estao no bd
		//qq alteracao q eu fizer, alterarah no bd!!
		
		
		// estado transient eh o estado em que a conta acabou de ser criada e ainda n eh
				// gerenciada pelo banco e se a aplicação morrer, a conta morre junto
		//ela passa a ser gerenciada qdo vc chama o metodo persist
		//metodo persist() = TRANSIENT -> MANAGED
		em.persist(conta);
		
		//como esta no estado managed, vai dar um update com a linha abaixo
		conta.setTitular("Roger");

		em.getTransaction().commit();
		
		//o estado managed ficará ate fecharmos o EntityManager				
		em.close();

	}
}
