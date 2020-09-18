package br.com.stapait;

public class Celula {
	private Object elemento;
	private Celula proximo;
	private Celula anterior;
	
	public Celula(Object elemento) {
		this(null, elemento);
	}
	public Celula(Celula proximo, Object elemento) {		
		this.elemento = elemento;
		this.proximo = proximo;
	}
	
	public Celula getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Celula anterior) {
		this.anterior = anterior;
	}
	public Object getElemento() {
		return elemento;
	}
	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}
	
	public Celula getProximo() {
		return proximo;
	}
	
	
}
