package br.com.stapait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class TestaPerformance {
	public static void main(String[] args) {
		Collection<Integer> numerosLista = new ArrayList<Integer>();
		Collection<Integer> numerosSet = new HashSet<Integer>();
		Collection<Integer> numerosSetSync = Collections.synchronizedSet(new HashSet<Integer>());
		Collection<Integer> numerosLinkedSet = new LinkedHashSet<Integer>();
        tempoTotal(numerosLista);
        tempoTotal(numerosSet);
        tempoTotal(numerosSetSync);
        tempoTotal(numerosLinkedSet);
    }

	private static void tempoTotal(Collection<Integer> numeros) {		
        long inicioAdd = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {
            numeros.add(i);
        }
        
        long fim = System.currentTimeMillis();

        long tempoDeExecucao = fim - inicioAdd;

        System.out.println("Tempo gasto Add: " + tempoDeExecucao);

        long inicioContains = System.currentTimeMillis();
        
        for (Integer numero : numeros) {
            numeros.contains(numero);
        }

        fim = System.currentTimeMillis();

        tempoDeExecucao = fim - inicioContains;

        System.out.println("Tempo gasto Contains: " + tempoDeExecucao);
	}
}
