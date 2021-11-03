package br.com.caelum.financas.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteTodasAsMovimentacoesDasContas {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		// Por padrão o join fetch fará um inner join que trará somente as contas que
		// possuem movimentação. Portanto, precisamos fazer um left join fetch em vez de
		// join fetch.
		String jpql = "select distinct c from ContaJPA c left join fetch c.movimentacoes";

		Query query = em.createQuery(jpql);

		List<ContaJPA> resultados = query.getResultList();

		for (ContaJPA contaJPA : resultados) {
			System.out.println("titular: " + contaJPA.getTitular());
			System.out.println("movimentacoes: ");
			// n+1 - "select c from ContaJPA c" e o codigo abaixo
			// sem n+ 1 - "select c from ContaJPA c join fetch c.movimentacoes" e o codigo
			// abaixo
			System.out.println(contaJPA.getMovimentacoes());

		}

	}

}