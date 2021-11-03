package br.com.caelum.financas.jpa.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteConsultaFuncaoMaxJPQL {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		ContaJPA contaJPA = em.find(ContaJPA.class, 1);
		
		String jpql = "select max(m.valor) from Movimentacao m where m.conta = :pConta";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", contaJPA);
		
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		
		System.out.println("o max eh : " + resultado);
		
		
	}

}
