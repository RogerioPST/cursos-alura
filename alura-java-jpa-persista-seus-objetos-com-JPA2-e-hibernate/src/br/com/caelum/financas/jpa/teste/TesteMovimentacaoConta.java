package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.Movimentacao;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		Movimentacao movimentacao = em.find(Movimentacao.class, 3);
		ContaJPA conta = movimentacao.getContaJPA();
		
		System.out.println(conta.getTitular());
		
		//o codigo abaixo funciona qdo vc tem o relacionamento bidirecional (oneToMany e ManyToOne nas classes)
		System.out.println(conta.getMovimentacoes().size());
		em.close();
		}

}
