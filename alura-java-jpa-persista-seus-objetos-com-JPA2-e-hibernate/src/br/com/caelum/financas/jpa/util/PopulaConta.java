package br.com.caelum.financas.jpa.util;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.ContaJPA;

public class PopulaConta {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		ContaJPA conta1 = new ContaJPA();
		ContaJPA conta2 = new ContaJPA();
		ContaJPA conta3 = new ContaJPA();
		ContaJPA conta4 = new ContaJPA();
		ContaJPA conta5 = new ContaJPA();

		conta1.setBanco("001 - BANCO DO BRASIL");
		conta1.setNumero("16987-8");
		conta1.setAgencia("6543");
		conta1.setTitular("Maria dos Santos");

		conta2.setBanco("237 - BANCO BRADESCO");
		conta2.setNumero("86759-1");
		conta2.setAgencia("1745");
		conta2.setTitular("Paulo Roberto Souza");

		conta3.setBanco("341 - BANCO ITAU UNIBANCO");
		conta3.setNumero("46346-3");
		conta3.setAgencia("4606");
		conta3.setTitular("Antonio Duraes");

		conta4.setBanco("033 - BANCO SANTANDER");
		conta4.setNumero("12345-6");
		conta4.setAgencia("9876");
		conta4.setTitular("Leandra Marques");

		conta5.setBanco("104 - CAIXA ECONOMICA FEDERAL");
		conta5.setNumero("98654-3");
		conta5.setAgencia("1234");
		conta5.setTitular("Alexandre Duarte");

		// persistindo as contas
		manager.persist(conta1);
		manager.persist(conta2);
		manager.persist(conta3);
		manager.persist(conta4);
		manager.persist(conta5);

		manager.getTransaction().commit();

		manager.close();

	}
}