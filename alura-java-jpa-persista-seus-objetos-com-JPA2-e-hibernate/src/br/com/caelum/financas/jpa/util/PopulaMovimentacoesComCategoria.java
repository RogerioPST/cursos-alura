package br.com.caelum.financas.jpa.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class PopulaMovimentacoesComCategoria {
	public static void main(String[] args) {
		
		Categoria categoria1 = new Categoria("viagem");
		Categoria categoria2 = new Categoria("negocios");
		
		ContaJPA conta = new ContaJPA();
		conta.setId(2);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance());
		movimentacao1.setDescricao("viagem a SP");
		movimentacao1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao1.setValor(new BigDecimal("100"));
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao1.setContaJPA(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		movimentacao2.setData(amanha);
		movimentacao2.setDescricao("viagem a RJ");
		movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao2.setValor(new BigDecimal("300"));
		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao2.setContaJPA(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(categoria1);
		em.persist(categoria2);		
		
		em.persist(movimentacao1);
		em.persist(movimentacao2);
		
		
		em.getTransaction().commit();
		em.close();
		
	}

}
