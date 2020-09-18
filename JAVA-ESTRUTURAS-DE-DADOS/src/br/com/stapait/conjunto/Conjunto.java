package br.com.stapait.conjunto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Conjunto {
	private ArrayList<LinkedList<String>> tabela = new ArrayList<>();
	
	public Conjunto() {
		//uma p cada letra do alfabeto
		for (int i = 0; i < 26; i++) {
			tabela.add(new LinkedList<String>());
		}
	}
	
	public void adiciona(String palavra) {
		if (!contem(palavra)) {		
			int indice = calculaIndiceDaTabela(palavra);
			List<String> lista = tabela.get(indice);
			lista.add(palavra);
		}
	}

	public void remove(String palavra) {
		if (contem(palavra)) {
			int indice = calculaIndiceDaTabela(palavra);
			List<String> lista = tabela.get(indice);
			lista.remove(palavra);
		}
	}
	private boolean contem(String palavra) {
		int indice = calculaIndiceDaTabela(palavra);
		return tabela.get(indice).contains(palavra);
	}

	private int calculaIndiceDaTabela(String palavra) {
		//eh a funcao q espalha (hash)
		//resto da divisao vai me dar um numero de 0 a 25
		return palavra.toLowerCase().charAt(0) % 26;
	}

	@Override
	public String toString() {

		return tabela.toString();				
	}
	
}
