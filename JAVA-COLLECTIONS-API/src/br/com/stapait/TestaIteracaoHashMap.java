package br.com.stapait;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TestaIteracaoHashMap {

	public static void main(String[] args) {

		Map<Integer, String> pessoas = new HashMap<>();

		pessoas.put(21, "Leonardo Cordeiro");
		pessoas.put(27, "Fabio Pimentel");
		pessoas.put(19, "Silvio Mattos");
		pessoas.put(23, "Romulo Henrique");
		
		
		Set<Integer> keySet = pessoas.keySet();
		for (Integer key : keySet) {
			System.out.println(pessoas.get(key));			
		}
		
		System.out.println("___");
		Map<Integer, String> pessoasLinked = new LinkedHashMap<>();

		pessoasLinked.put(21, "Leonardo Cordeiro");
		pessoasLinked.put(27, "Fabio Pimentel");
		pessoasLinked.put(19, "Silvio Mattos");
		pessoasLinked.put(23, "Romulo Henrique");
		
		
		Set<Integer> keySetLinked = pessoasLinked.keySet();
		for (Integer key : keySetLinked) {
			System.out.println(pessoasLinked.get(key));			
		}
	}

}
