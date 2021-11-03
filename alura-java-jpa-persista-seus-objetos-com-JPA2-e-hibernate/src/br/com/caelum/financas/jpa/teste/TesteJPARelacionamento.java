package br.com.caelum.financas.jpa.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TesteJPARelacionamento {
	public static void main(String[] args) {

	    ContaJPA conta = new ContaJPA();
	    conta.setAgencia("0102");
	    conta.setBanco("Itau");
	    conta.setNumero("1234");
	    conta.setTitular("Leonardo");

	    Movimentacao movimentacao = new Movimentacao();
	    movimentacao.setData(Calendar.getInstance());
	    movimentacao.setDescricao("Churrascaria");
	    movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
	    movimentacao.setValor(new BigDecimal("200.0"));

	    movimentacao.setContaJPA(conta);

	    EntityManager em = new JPAUtil().getEntityManager();
	    em.getTransaction().begin();

	    em.persist(conta);
	    //soh posso persistir uma conta dentro de movimentacao, se a conta estiver no estado MANAGED
	    em.persist(movimentacao);

	    em.getTransaction().commit();
	    em.close();
	}

}
