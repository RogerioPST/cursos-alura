package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.financas.modelo.ContaJDBC;

public class ContaDAO {

	private Connection connection;

	public ContaDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(ContaJDBC conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("insert into Conta (titular, banco, agencia, numero) values (?,?,?,?)");
			ps.setString(1, conta.getTitular());
			ps.setString(2, conta.getBanco());
			ps.setString(3, conta.getAgencia());
			ps.setString(4, conta.getNumero());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(ContaJDBC conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("update Conta set titular=?, banco=?, agencia=?, numero=? where id=?");
			ps.setString(1, conta.getTitular());
			ps.setString(2, conta.getBanco());
			ps.setString(3, conta.getAgencia());
			ps.setString(4, conta.getNumero());
			ps.setInt(5, conta.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(ContaJDBC conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("delete from Conta where id=?");
			ps.setInt(1, conta.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ContaJDBC procura(Integer id) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			ContaJDBC conta = new ContaJDBC();
			conta.setTitular(rs.getString("titular"));
			conta.setBanco(rs.getString("banco"));
			conta.setNumero(rs.getString("numero"));
			conta.setAgencia(rs.getString("agencia"));
			ps.close();
			return conta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ContaJDBC> listaPaginada(int primeiro, int quantidade) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta limit ?,? ");
			ps.setInt(1, primeiro);
			ps.setInt(2, quantidade);

			ResultSet rs = ps.executeQuery();

			List<ContaJDBC> lista = new ArrayList<ContaJDBC>();

			while (rs.next()) {
				ContaJDBC conta = new ContaJDBC();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ContaJDBC> lista() {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta");
			ResultSet rs = ps.executeQuery();

			List<ContaJDBC> lista = new ArrayList<ContaJDBC>();

			while (rs.next()) {
				ContaJDBC conta = new ContaJDBC();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ContaJDBC> procuraPeloNome(String nome) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta where titular like ?");
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();

			List<ContaJDBC> lista = new ArrayList<ContaJDBC>();

			while (rs.next()) {
				ContaJDBC conta = new ContaJDBC();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
