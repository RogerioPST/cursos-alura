package br.com.caelum.financas.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.Movimentacao;

public class TesteInserirMovimentacao {

    public static void main(String[] args) {

        EntityManager em = new JPAUtil().getEntityManager();

        ContaJPA conta = em.find(ContaJPA.class, 1);

        List<Movimentacao> movimentacoes = conta.getMovimentacoes();

        em.close();

        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Movimentação: " + movimentacao.getDescricao());
        }

    }
}
