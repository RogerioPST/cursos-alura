package br.com.caelum.financas.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TesteJPQL {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		ContaJPA conta = new ContaJPA();
		conta.setId(2);

		
		//String jpql = "select m from Movimentacao m where m.conta.id =2 ";
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: "+ movimentacao.getDescricao());
			System.out.println("id conta: "+ movimentacao.getContaJPA().getId());
			
		}
		
		

		em.getTransaction().commit();

	}

}
