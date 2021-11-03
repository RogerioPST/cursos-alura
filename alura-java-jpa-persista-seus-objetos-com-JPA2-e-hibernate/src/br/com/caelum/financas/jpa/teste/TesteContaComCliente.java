package br.com.caelum.financas.jpa.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.jpa.util.JPAUtil;
import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.ContaJPA;

public class TesteContaComCliente {
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Leo");
		cliente.setProfissao("professor");
		cliente.setEndereco("rua prof 1");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Leo C");
		cliente2.setProfissao("professor");
		cliente2.setEndereco("rua prof 1");
		
		ContaJPA conta = new ContaJPA();
		conta.setId(2);
		
		cliente.setConta(conta);
		cliente2.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(cliente);
		//em.persist(cliente2);
		
		em.getTransaction().commit();
		
		
		
		
		
	}

}
