package br.com.stapait;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestaTreeSetNoRecibo {
	public static void main(String[] args) {
		Recibo r1 = new Recibo("games");
		Recibo r2 = new Recibo("lazer");
		
//		Set<Recibo> recibos = new TreeSet<>();	
		Set<Recibo> recibos = new TreeSet<>(Comparator.comparing(Recibo::getOrigem));
		recibos.add(r1);
		recibos.add(r2);
		
		for (Recibo recibo : recibos) {
			System.out.println(recibo.getOrigem());
			
		}
		
		List<String> letras = new LinkedList<>();
		letras.add("A");
		letras.add("B");
		letras.add("C");

		letras.forEach(letra -> {
		    System.out.println(letra);
		});
		
		Iterator<String> iterador = letras.iterator();
		while(iterador.hasNext()) {
			String proximo = iterador.next();
			System.out.println(proximo);
		}
	}
}
