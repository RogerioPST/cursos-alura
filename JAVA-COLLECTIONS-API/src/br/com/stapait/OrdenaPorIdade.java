package br.com.stapait;

import java.util.Comparator;

public class OrdenaPorIdade implements Comparator<Funcionario> {

	@Override
	public int compare(Funcionario f1, Funcionario f2) {

		return f1.getNome().compareTo(f2.getNome());
	}
}