package br.com.stapait;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {

	private Class<?> classe;

	public ManipuladorClasse(Class<?> classe) {
		this.classe = classe;
	}

	public ManipuladorConstrutor getContrutorPadrão() {
		try {
			Constructor<?> construtorPadrao = classe.getDeclaredConstructor();
			return new ManipuladorConstrutor(construtorPadrao);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ManipuladorObjeto criaInstancia() {
		Object instancia = getContrutorPadrão().invoca();
		return new ManipuladorObjeto(instancia);
		
	}

}
