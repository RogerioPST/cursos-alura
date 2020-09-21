package br.com.stapait;

public class Request {

	private String nomeControle;
	private String nomeMetodo;

	public Request(String url) {
		// nomeControle/nomeMetodo
		String[] partesUrl = url.replaceFirst("/", "").split("/");
		char primeiraLetraControleMaiuscula = Character.toUpperCase(partesUrl[0].charAt(0));
		String restanteControle = partesUrl[0].substring(1);

		nomeControle = primeiraLetraControleMaiuscula + restanteControle + "Controller";
		nomeMetodo = partesUrl[1];

	}

	public String getNomeControle() {
		return nomeControle;

	}
	public String getNomeMetodo() {
		return nomeMetodo;
	}
}
