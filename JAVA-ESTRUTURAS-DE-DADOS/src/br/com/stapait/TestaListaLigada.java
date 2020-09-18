package br.com.stapait;

import java.util.LinkedList;

public class TestaListaLigada {
	public static void main(String[] args) {
		LinkedList<Object> linkedList = new LinkedList<>();
		ListaLigada lista = new ListaLigada();
		System.out.println(lista);
		lista.adicionaNoComeco("Roger");
		System.out.println(lista);
		lista.adicionaNoComeco("Rogerio");		
		System.out.println(lista);
		lista.adicionaNoComeco("Paulo");		
		System.out.println(lista);
		
		lista.adicionaNoFim("Marcelo");
		System.out.println(lista);
		System.out.println(lista.tamanho());
		lista.adiciona(2, "Gabriel");
		System.out.println(lista);
		
		Object elementoBuscado = lista.pega(2);
		System.out.println(elementoBuscado);
		
		lista.removeDoComeco();
		System.out.println(lista);
		lista.removeDoFim();
		System.out.println(lista);
		
		lista.adicionaNoFim("Jose");
		lista.adicionaNoFim("Joao");
		System.out.println(lista);
		lista.remove(2);
		System.out.println(lista);
		
		System.out.println(lista.contem("Rogerio"));
		System.out.println(lista.contem("Danilo"));
		
		
	}
}
