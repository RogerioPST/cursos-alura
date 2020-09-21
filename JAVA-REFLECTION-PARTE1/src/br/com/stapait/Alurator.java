package br.com.stapait;

import java.lang.reflect.InvocationTargetException;

public class Alurator {
	
	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao
		
		// /produto/lista
		Request request = new Request(url);
		String nomeControle  = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		
		/* try { */
		/*
		 * Object instanciaControle = new Reflexao()
		 * .refletClasse(pacoteBase+nomeControle) .getContrutorPadrão() .invoca();
		 */
			
		Object retorno = new Reflexao().refletClasse(pacoteBase+nomeControle)
				.criaInstancia()
				.getMetodo(nomeMetodo)
				.invoca();
				
			/*
			 * Class<?> classeControle = Class.forName(pacoteBase + nomeControle); Object
			 * instanciaControle = classeControle.getDeclaredConstructor().newInstance();
			 */
		/* System.out.println(instanciaControle); */
		System.out.println(retorno);
			return retorno;
		/*
		 * } catch (ClassNotFoundException | InstantiationException
		 * |IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
		 * SecurityException e) { e.printStackTrace(); throw new RuntimeException(e); }
		 * catch (InvocationTargetException e) { e.printStackTrace(); throw new
		 * RuntimeException("Erro no construtor! exception original: " ,
		 * e.getTargetException()); }
		 */
	}
}
