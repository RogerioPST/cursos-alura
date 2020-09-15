package br.com.stapait;

public class Aula implements Comparable<Aula>{
	private String titulo;
	private int tempo;
	
	public Aula(String titulo, int tempo) {
		if (titulo == null) {
			titulo = "";
		}
		this.titulo = titulo;
		this.tempo = tempo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Aula: "+ this.titulo+ ", " + this.tempo + "minutos]";
	}

	@Override
	public int compareTo(Aula outraAula) {
		// TODO Auto-generated method stub
		return this.titulo.compareTo(outraAula.titulo);
	}
}
