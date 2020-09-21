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
}