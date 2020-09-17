package br.com.stapait;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class TestaCursoComAluno {
	public static void main(String[] args) {
		 Set<String> nomes = Collections.emptySet();
		 //nomes.add("Paulo"); //o que acontece aqui? UnsupportedOperationException!!!!
	        
		Curso c = new Curso("Dominando", "Paulo");
		
		c.adiciona(new Aula("Trabalhando 3", 21));
		c.adiciona(new Aula("Trabalhando 2", 11));
		c.adiciona(new Aula("Trabalhando 1", 211));
		
		Aluno a1 = new Aluno("Rogerio", 34672);
		Aluno a2 = new Aluno("Ines", 346723);
		Aluno a3 = new Aluno("Vanessa", 346722);
		
		c.matricula(a1);
		c.matricula(a2);
		c.matricula(a3);
		
		System.out.println("todos os alunos matriculados:");
		c.getAlunos().forEach(aluno ->{
			System.out.println(aluno);
		});
		
		Aluno rogerio = new Aluno("Rogerio", 34672);
		
		System.out.println(c.estaMatriculado(a1));
		System.out.println("e o Rogerio, esta matriculado?");
		System.out.println(c.estaMatriculado(rogerio));
		System.out.println("o obj a1 é igual ao obj rogerio?");
		System.out.println(a1.equals(rogerio));
		System.out.println("hash");
		System.out.println(rogerio.hashCode());
		System.out.println("hash");
		
		System.out.println(a1.hashCode());
		
		//desde o java 5
		Set<Aluno> alunos = c.getAlunos();
		Iterator<Aluno> iterador = alunos.iterator();
		while (	iterador.hasNext()) {
			Aluno proximo = iterador.next();
			System.out.println("iterador: "+proximo);
		}
	}
	
	

}
