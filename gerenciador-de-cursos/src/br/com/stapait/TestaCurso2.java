package br.com.stapait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestaCurso2 {
	public static void main(String[] args) {
		Curso c = new Curso("Dominando coleções", "Paulo");
				
		c.adiciona(new Aula("Trabalhando 3", 21));
		c.adiciona(new Aula("Trabalhando 2", 20));
		c.adiciona(new Aula("Trabalhando 23", 29));
		
		List<Aula> aulasImutaveis = c.getAulas();
		System.out.println(aulasImutaveis);
		
		List<Aula> aulasMutaveis = new ArrayList<>(aulasImutaveis);
		Collections.sort(aulasMutaveis);
		System.out.println(aulasMutaveis);
		Collections.sort(aulasMutaveis, Comparator.comparing(Aula::getTempo));
		System.out.println(aulasMutaveis);
		
		aulasMutaveis.sort(Comparator.comparing(Aula::getTempo));
		System.out.println(aulasMutaveis);
		System.out.println(c.getTempoTotal());
		System.out.println(c);
		
		List<List<Aula>> singletonList = Collections.singletonList(c.getAulas());
		System.out.println("singletonList: "+ singletonList);
		List<List<Aula>> nCopies = Collections.nCopies(10, c.getAulas());
		System.out.println("nCopies:"+ nCopies);
		//Este método também é utilizado para inicializar Listas recém criadas com Null, já que ele pode rapidamente criar diversos objetos, deste modo:
		List<Aula> nCopies2 = Collections.nCopies(10, (Aula)null);
		System.out.println("nCopies2:"+ nCopies2);
	}

}
