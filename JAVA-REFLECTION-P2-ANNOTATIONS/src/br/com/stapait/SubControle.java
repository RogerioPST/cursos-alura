package br.com.stapait;

public class SubControle extends Controle{
	public SubControle() {

	}
	
	public SubControle(int n) {

	}

	private SubControle(String s) {

	}	
	
	public void metodoSubControle1() {
		System.out.println("executando metodo SubControle.metodoSubControle1()");
	}

	private String metodoSubControle2() {
		System.out.println("executando metodo SubControle.metodoSubControle2()");
		return "retorno do metodo SubControle.metodoSubControle2()";
	}
}
