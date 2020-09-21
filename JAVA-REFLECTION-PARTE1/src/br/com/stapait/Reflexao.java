package br.com.stapait;

public class Reflexao {

	public ManipuladorClasse refletClasse(String fullQualifiedName) {
		try {
			Class<?> classe = Class.forName(fullQualifiedName);
			return new ManipuladorClasse(classe);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

}
