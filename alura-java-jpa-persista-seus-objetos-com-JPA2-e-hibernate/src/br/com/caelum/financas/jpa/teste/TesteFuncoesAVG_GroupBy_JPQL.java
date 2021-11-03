package br.com.caelum.financas.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.jpa.dao.MovimentacaoDAO;
import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TesteFuncoesAVG_GroupBy_JPQL {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		ContaJPA conta = new ContaJPA();
		conta.setId(2);
		
		MovimentacaoDAO dao = new MovimentacaoDAO(em);
		List<Double> medias = dao.getMediasPorDiaEPorTipo(TipoMovimentacao.SAIDA, conta);

		System.out.println("a avg de um dia eh : " + medias.get(0));
		System.out.println("a avg de um outro dia eh : " + medias.get(1));

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
