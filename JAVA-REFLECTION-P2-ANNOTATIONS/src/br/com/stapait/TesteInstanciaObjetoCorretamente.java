package br.com.stapait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjetoCorretamente {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<SubControle> subControleClasse1 = SubControle.class;
		
		Class<?> subControleClasse2 = Class.forName("br.com.stapait.SubControle");
		Class<?> controleClasse1 = Class.forName("br.com.stapait.Controle");
		
		Constructor<SubControle> construtorPublicoSubControleSemParametro = subControleClasse1.getConstructor();
		Constructor<SubControle> construtorPublicoSubControleComParametro = subControleClasse1.getConstructor(int.class);
		
		Constructor<SubControle> construtorPrivadoSubControleComParametro = subControleClasse1.getDeclaredConstructor(String.class);
		
		
		System.out.println(construtorPublicoSubControleSemParametro);
		System.out.println(construtorPublicoSubControleComParametro);
		System.out.println(construtorPrivadoSubControleComParametro);
		
		SubControle subControle = construtorPublicoSubControleSemParametro.newInstance();
		System.out.println(subControle instanceof SubControle);
		construtorPrivadoSubControleComParametro.setAccessible(true);
		SubControle subControlePrivado = construtorPrivadoSubControleComParametro.newInstance("oi");
		System.out.println(subControle instanceof SubControle);
		
	}

}
