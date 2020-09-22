package br.com.stapait;

import java.lang.reflect.Field;

public class TesteManipulaAtributos {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object produto = new Produto("Prod 1", 20, "marca 1");
		Class<? extends Object> classe = produto.getClass();
		
		for(Field atributo : classe.getDeclaredFields()) {
			atributo.setAccessible(true);			
			System.out.println(atributo.getName() 
					+ ":" + atributo.get(produto));
		}
		
		System.out.println(classe.getField("id"));
		
		for(Field atributo : classe.getFields()) {			
			System.out.println(atributo.getName() 
					+ ":" + atributo.get(produto));
		}
	}
}
