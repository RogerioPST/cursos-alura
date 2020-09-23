package br.com.stapait;

public class Reflexao {

	public ManipuladorClasse refletClasse(String fullQualifiedName) {
		
			Class<?> classe = getClasse(fullQualifiedName);
			return new ManipuladorClasse(classe);
				
	}

	public Class<?> getClasse(String fullQualifiedName) {
		try {
			Class<?> classe = Class.forName(fullQualifiedName);
			return classe;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		
	}

}
