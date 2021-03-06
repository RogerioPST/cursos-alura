package br.com.stapait.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD } )
public @interface NomeTagXml {	
	public String value(); //por conven��o, toda anota��o q tem um unico atributo.	

}
