package br.com.caelum.financas.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.ContaJPA;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDAO {
	
			private EntityManager em;						

			public MovimentacaoDAO(EntityManager em) {
				this.em = em;
				// TODO Auto-generated constructor stub
			}
	
			public List<Double> getMediasPorDiaEPorTipo(TipoMovimentacao saida, ContaJPA conta) {
				// TODO Auto-generated method stub

				// String jpql = "select m from Movimentacao m where m.conta.id =2 ";

				String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta group by day(m.data), month(m.data), year(m.data) ";

				TypedQuery<Double> query = em.createQuery(jpql, Double.class);
				query.setParameter("pConta", conta);

				// query.setParameter("pTipo", TipoMovimentacao.ENTRADA);

				return query.getResultList();				
				
			}

}
