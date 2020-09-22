package br.com.stapait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesteInvocaMetodoComParametro {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> controleClasse = Class.forName("br.com.stapait.Controle");
		Constructor<?> construtorPadrao = controleClasse.getDeclaredConstructor();
		
		Object instancia = construtorPadrao.newInstance();
		
		Method metodo = controleClasse.getDeclaredMethod("metodoControle2", String.class);
		
		
		Object retorno = metodo.invoke(instancia, "show");
		
		System.out.println(retorno);
		
		metodo = controleClasse.getDeclaredMethod("metodoControle2", String.class, Integer.class);
		
		
		retorno = metodo.invoke(instancia, "show", 1);
		
		System.out.println(retorno);
		
	}
}
