package br.com.stapait;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String nomeControle;
	private String nomeMetodo;
	private Map<String, Object> queryParams;

	public Request(String url) {
		/*
		 * caso possiveis:
		 * nomeControle/nomeMetodo
		 * nomeControle/nomeMetodo?param1=valor1&param2=valor2
		 * /produto/filtra?marca=marca 1&nome=produto
		 */
		//caracter ? eh especial em regex e precisa ser envolvido por colchete
		String[] partesUrl = url.replaceFirst("/", "").split("[?]");
		
		String[] controleEMetodo = partesUrl[0].split("/");

		
		char primeiraLetraControleMaiuscula = Character.toUpperCase(controleEMetodo[0].charAt(0));
		String restanteControle = controleEMetodo[0].substring(1);

		nomeControle = primeiraLetraControleMaiuscula + restanteControle + "Controller";
		nomeMetodo = controleEMetodo[1];
		
		queryParams = partesUrl.length > 1 
				? new QueryParamsBuilder().withParams(partesUrl[1]).build() 
				: new HashMap<String, Object>(); 

	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}
	
	public String getNomeControle() {
		return nomeControle;

	}
	public String getNomeMetodo() {
		return nomeMetodo;
	}
}
