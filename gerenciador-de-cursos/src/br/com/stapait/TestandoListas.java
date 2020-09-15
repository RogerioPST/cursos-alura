package br.com.stapait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestandoListas {
	
	public static void main(String[] args) {
		String aula1 = "Conhecendo mais de listas";
		String aula2 = "Modelando a class Aula";
		String aula3 = "Trabalhando com Cursos e Sets";
		
		List<String> aulas = new ArrayList<>();
		aulas.add(aula1);
		aulas.add(aula2);
		aulas.add(aula3);
		
		System.out.println(aulas);
		aulas.remove(0);
		
		System.out.println(aulas);
		
		for (String aula : aulas) {
			System.out.println("Aula: "+ aula);
		}
		
		String primeiraAula = aulas.get(0);
		
		System.out.println("A primeira aula é "+ primeiraAula);
		
		aulas.forEach(aula -> {
			System.out.println("percorrendo :" + aula);
		});
		
		aulas.add("Aumentando nosso conhecimento");
		
		System.out.println(aulas);
		
		System.out.println("depois de ordenado:");
		Collections.sort(aulas);
		System.out.println(aulas);
		 
	}

}
