package br.com.stapait.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD } )
public @interface NomeTagXml {
	
	//public String value(); //por convenção, toda anotação q tem um unico atributo
	//, recebe o nome de value.
	//daih, n precisa passar value="produto" em @NomeTagXml(valor="produto", novoValor="teste")
	//em vez disso, passa apenas  @NomeTagXml("produto")
	public String valor();
	public String novoValor();

}
