package br.com.stapait.anotacao;

import br.com.stapait.Produto;

public class TesteManipulacaoAnotacao {
	public static void main(String[] args) {
		Produto produto = new Produto("p1", 20, "m1");
		Class<?> classe = produto.getClass();
		
		NomeTagXml declaredAnnotation = classe.getDeclaredAnnotation(NomeTagXml.class);
		AnotacaoTeste declaredAnnotation2 = classe.getDeclaredAnnotation(AnotacaoTeste.class);
		//nome da propriedade definida na annotation
		System.out.println(declaredAnnotation.value());
		System.out.println(declaredAnnotation2.valorInicial());
	}
}
