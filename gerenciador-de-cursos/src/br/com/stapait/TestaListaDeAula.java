package br.com.stapait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestaListaDeAula {

	public static void main(String[] args) {
		Aula a1 = new Aula("Revendo Lists", 21);
		Aula a2 = new Aula("Vendo hashcode", 20);
		Aula a3 = new Aula("Lista de objetos", 23);
		String novo = null;
		Aula a4 = new Aula(novo, 15);
		
		List<Aula> aulas = new ArrayList<>();
		
		aulas.add(a1);
		aulas.add(a2);
		aulas.add(a3);
		aulas.add(a4);
		
		
		System.out.println(aulas);
		
		Collections.sort(aulas);
		
		System.out.println(aulas);
		
		Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
		
		System.out.println(aulas);
		
		aulas.sort(Comparator.comparing(Aula::getTempo));
		
		System.out.println(aulas);
	}
}
