package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteConsultaFuncaoCountJPQL {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		ContaJPA contaJPA = em.find(ContaJPA.class, 1);
		
		String jpql = "select count(m) from Movimentacao m where m.conta = :pConta";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", contaJPA);
		
		Long resultado = (Long) query.getSingleResult();
		
		System.out.println("o count eh : " + resultado);
		
		
	}

}
