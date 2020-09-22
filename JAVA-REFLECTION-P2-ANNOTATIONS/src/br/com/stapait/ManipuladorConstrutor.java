package br.com.stapait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {

	private Constructor<?> construtorPadrao;

	public ManipuladorConstrutor(Constructor<?> construtorPadrao) {
		this.construtorPadrao = construtorPadrao;
	}

	public Object invoca() {
		try {
			return construtorPadrao.newInstance();
		} catch (InstantiationException | IllegalAccessException 
				| IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("exception original:", e.getTargetException());
		}
		
	}

}
