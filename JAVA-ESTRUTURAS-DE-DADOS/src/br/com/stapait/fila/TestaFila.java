package br.com.stapait.fila;

public class TestaFila {
	public static void main(String[] args) {
		Fila fila = new Fila();
		fila.adiciona("Rogerio");
		System.out.println(fila);
		fila.adiciona("Roger");
		System.out.println(fila);
		System.out.println(fila.estaVazia());
		System.out.println(fila.remove());
		System.out.println(fila);
		System.out.println(fila.estaVazia());
		System.out.println(fila.remove());
		System.out.println(fila);
		System.out.println(fila.estaVazia());
	}
}
