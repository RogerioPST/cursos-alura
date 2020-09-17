package br.com.stapait;

public class Aluno {
	private String nome;
	
	public Aluno(String nome) {		
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((nome == null) ? 0 : nome.hashCode()); return
	 * result; }
	 */

	@Override
	public boolean equals(Object obj) {
		Aluno outro = (Aluno) obj;
		return outro.nome.equals(this.nome);
		
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	
}
