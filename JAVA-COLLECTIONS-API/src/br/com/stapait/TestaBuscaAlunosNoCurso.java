package br.com.stapait;

public class TestaBuscaAlunosNoCurso {
	public static void main(String[] args) {
		Curso c = new Curso("Dominando", "Paulo");
		
		c.adiciona(new Aula("A1", 21));
		c.adiciona(new Aula("AS2", 15));
		c.adiciona(new Aula("as3", 3));
		
		Aluno a1 = new Aluno("Roger", 21234);
		Aluno a2 = new Aluno("Rogerio", 221234);
		
		c.matricula(a1);
		c.matricula(a2);
		
		System.out.println(c.buscaMatriculado(21234));
	}
}
