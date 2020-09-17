package br.com.stapait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class TestaAlunos {
	public static void main(String[] args) {
		Collection<String> alunos = new HashSet<>();
		alunos.add("Rogerio");
		alunos.add("Vanessa");
		alunos.add("Paulo");
		
		boolean pauloEstaMatriculado = alunos.contains("Paulo");
		alunos.remove("Rogerio");
		System.out.println(pauloEstaMatriculado);
		System.out.println(alunos.size());
		for (String aluno : alunos) {
			System.out.println(aluno);			
		}
		alunos.forEach(aluno -> {
			System.out.println(aluno);
		});
		boolean adicionou = alunos.add("Pedro");
		System.out.println("Pedro foi adicionado ao Set? " + adicionou);
		boolean adicionou2 = alunos.add("Pedro");
		System.out.println("Pedro foi adicionado ao Set? " + adicionou2);
		//todos os filhos de Set n tem o método get e tanto contains como remove são mto rapidos
		//se eu precisar ordenar, se a ordem dos elementos importar, posso passar o Set no construtor de ArrayList
		ArrayList<String> alunosEmLista = new ArrayList<String>(alunos);
		
	}
}
