package br.com.caelum.financas.jpa.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.jpa.dao.MovimentacaoDAO;
import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TesteFuncoesJPQL {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		ContaJPA conta = new ContaJPA();
		conta.setId(2);

		
		
		
		//String jpql = "select m from Movimentacao m where m.conta.id =2 ";
		
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta  order by m.valor desc";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		
		//query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		BigDecimal valor = (BigDecimal) query.getSingleResult();
		
		System.out.println("a soma eh: " + valor);
		
//		List<Movimentacao> resultados = query.getResultList();
//		
//		for (Movimentacao movimentacao : resultados) {
//			System.out.println("Descricao: "+ movimentacao.getDescricao());
//			System.out.println("id conta: "+ movimentacao.getContaJPA().getId());
//			
//		}
//		
		

		em.getTransaction().commit();

	}

}
