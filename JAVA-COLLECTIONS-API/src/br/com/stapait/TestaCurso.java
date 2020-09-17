package br.com.stapait;

import java.util.List;

public class TestaCurso {
	public static void main(String[] args) {
		Curso c1 = new Curso("Dominando", "Paulo");
		List<Aula> aulas = c1.getAulas();
		
		System.out.println(aulas);
		Aula a1 = new Aula("Trabalhando com ArrayList", 21);
		c1.adiciona(new Aula("Trabalhando com ArrayList", 21));
		System.out.println(aulas == c1.getAulas());
		System.out.println(aulas);
		
		aulas.add(a1);
	}
}
