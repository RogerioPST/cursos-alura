package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.financas.modelo.ContaJDBC;

public class TesteJDBCHSQLDB {

	public static void main(String[] args) throws SQLException {

		ContaJDBC conta = new ContaJDBC();
		conta.setTitular("Joao Ferreira");
		conta.setBanco("Itau");
		conta.setAgencia("0063");
		conta.setNumero("432561");

		Connection con = new ConnectionFactory().getConnectionHSQLDB();

		new BD(con).geraTabelaContas();

		con.setAutoCommit(false);

		ContaDAO dao = new ContaDAO(con);
		dao.adiciona(conta);

		List<ContaJDBC> contas = dao.lista();

		for (ContaJDBC c : contas) {
			System.out.println(c.getTitular());
		}

		con.commit();
		con.close();
	}

}
