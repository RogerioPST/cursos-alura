package br.com.stapait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TesteListas {

	public static void main(String[] args) {

		System.out.println("**** ArrayList vs LinkedList ***");

		List<Integer> numerosArrayList = new ArrayList<>();
		List<Integer> numerosLinkedList = new LinkedList<>();
		int quantidadeElementos = 2000000;

		long tempoArrayList = insereElementosNo(numerosArrayList, quantidadeElementos);
		long tempoLinkedList = insereElementosNo(numerosLinkedList, quantidadeElementos);

		System.out.println("Inser��o na ArrayList demorou  " + tempoArrayList);
		System.out.println("Inser��o na LinkedList demorou " + tempoLinkedList);

		tempoArrayList = removePrimeirosElementos(numerosArrayList);
		tempoLinkedList = removePrimeirosElementos(numerosLinkedList);

		System.out.println("Remo��o da ArrayList demorou  " + tempoArrayList);
		System.out.println("Remo��o da LinkedList demorou " + tempoLinkedList);
		
		tempoArrayList = buscaElemento(numerosArrayList);
		tempoLinkedList = buscaElemento(numerosLinkedList);

		System.out.println("Busca na ArrayList demorou  " + tempoArrayList);
		System.out.println("Busca na LinkedList demorou " + tempoLinkedList);
	}

	/*
	 * removendo 100 elementos sempre na primeira posi��o
	 */
	private static long removePrimeirosElementos(List<Integer> numeros) {
		long ini = System.currentTimeMillis();

		for (int i = 0; i < 100; i++) {
			numeros.remove(0); // removendo sempre o primeiro elemento
		}
		long fim = System.currentTimeMillis();
		return fim - ini;
	}

	private static long insereElementosNo(List<Integer> numeros, int quantidade) {
		long ini = System.currentTimeMillis();
		for (int i = 0; i < quantidade; i++) {
			numeros.add(i);
		}
		long fim = System.currentTimeMillis();
		return fim - ini;
	}
	
	private static long buscaElemento(List<Integer> numeros) {
		long ini = System.currentTimeMillis();

		numeros.get(999900);
		long fim = System.currentTimeMillis();
		return fim - ini;
	}
}
