package br.com.stapait.pilha;

public class TestaPilha {

	public static void main(String[] args) {
		Pilha pilha = new Pilha();
		pilha.insere("Rogerio");
		
		System.out.println(pilha);
		pilha.insere("Roger");
		System.out.println(pilha);
		
		System.out.println(pilha.remove());
		System.out.println(pilha.estaVazia());
		System.out.println(pilha);
		System.out.println(pilha.remove());
		System.out.println(pilha.estaVazia());
		System.out.println(pilha);
	}
}
