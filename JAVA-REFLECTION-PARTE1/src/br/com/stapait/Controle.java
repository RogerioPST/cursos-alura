package br.com.stapait;

import java.util.ArrayList;
import java.util.List;

public class Controle {	
	public Controle() {
	}
	public Controle(String s) {
	}
	private Controle(String s, String t) {
	}
	private List<String> lista = new ArrayList<>();	
	
	public List<String> getLista() {
		lista.add("item 1");
		lista.add("item 2");
		lista.add("item 3");		
		return lista;
	}
	
	private void metodoControle1() {
		
	}
	public void metodoControle2(String p1) {
		System.out.println("Controle.metodoControle2(String p1)");
		System.out.println("param obtido p1: " + p1);		
	}
	public void metodoControle2(String p1, String p2) {
		System.out.println("Controle.metodoControle2(String p1, String p2)");
		System.out.println("param obtido p1: " + p1);
		System.out.println("param obtido p2: " + p2);
	}
	public void metodoControle2(String p1, Integer p2) {
		System.out.println("Controle.metodoControle2(String p1, Integer p2)");
		System.out.println("param obtido p1: " + p1);
		System.out.println("param obtido p2: " + p2);
	}
}