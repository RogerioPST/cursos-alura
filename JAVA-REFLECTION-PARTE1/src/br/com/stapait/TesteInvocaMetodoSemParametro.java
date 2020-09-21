package br.com.stapait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesteInvocaMetodoSemParametro {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> subControleClasse = Class.forName("br.com.stapait.SubControle");
		
		Constructor<?> declaredConstructor = subControleClasse.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		
		Object newInstance = declaredConstructor.newInstance();
		for (Method m : subControleClasse.getMethods()) {
			System.out.println(m);
		}
		for (Method m : subControleClasse.getDeclaredMethods()) {
			System.out.println(m);
		}
		
		System.out.println();
		
		Method metodo = subControleClasse.getDeclaredMethod("metodoSubControle2");
		metodo.setAccessible(true);
		Object retorno = metodo.invoke(newInstance);
		System.out.println(retorno);
	}
}
