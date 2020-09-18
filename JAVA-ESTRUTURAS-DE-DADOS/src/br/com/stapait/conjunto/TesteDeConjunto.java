package br.com.stapait.conjunto;

public class TesteDeConjunto {
	public static void main(String[] args) {
		Conjunto conjunto = new Conjunto();
		conjunto.adiciona("Rogerio");
		System.out.println(conjunto);
		conjunto.adiciona("Rogerio");
		System.out.println(conjunto);
		conjunto.adiciona("Roger");
		System.out.println(conjunto);
		conjunto.adiciona("Gui");
		System.out.println(conjunto);
		conjunto.remove("Rogerio");
		System.out.println(conjunto);
	}
}
